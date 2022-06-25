package com.example.cosmetic.controller;

import com.example.cosmetic.dto.Member;
import com.example.cosmetic.dto.MemberEntity;
import com.example.cosmetic.repository.MemberRepository;
import com.example.cosmetic.service.MemberService;
import com.example.cosmetic.service.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class MemberController {


    private final MemberService memberService;
    private MemberRepository memberRepository;

    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("pagetitle", "COSVIEW");

        return "Login/Singup";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberEntity memberEntity, RedirectAttributes ra){
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Member member1 = new Member();
            member1.setName(memberEntity.getName());
            member1.setName2(memberEntity.getName2());
            member1.setId(memberEntity.getId());
            member1.setPw(passwordEncoder.encode(memberEntity.getPw()));
            member1.setNickname(memberEntity.getNickname());
            member1.setEmail(memberEntity.getEmail());
            member1.setAddr(memberEntity.getAddr());
            member1.setGender(memberEntity.getGender());

            ra.addFlashAttribute("message","The user has been saved successfully.");

            memberService.join(member1);

            if (member1.getId() != null) {
                return "redirect:/Login/LoginCompletion";
            } else {
                return "members/new";
            }
        }catch (Exception e){
            System.out.println("아이디 중복으로 에러 발생");
            return "Login/Singup";
        }
    }

    @RequestMapping(value = "/member/idChk", method = {RequestMethod.POST})
    public void memberChk(HttpServletResponse res, HttpServletRequest req, Model model) throws IOException {
        //request 요청 파라미터로 넣어온 값 중 userid 를 가져와서 저장
        String username = req.getParameter("username");

        //아이디 중복 체크를 확인하기 위한 변수
        boolean result = true;

        try{
            System.out.println("username: " + username);

            //findById 로 DB에 아이디가 저장되어있는지 여부 확인
            //만약 저장되어 있다면 chkID 값에 DB 에 있는 아이디가 찾아진 후 result = false
            //아니면 데이터를 찾을 수 없어 에러가 발생할 것임
            String chkID = memberRepository.findByName(username).getName();
            System.out.println("DB: " + chkID);
            if(chkID.equals(username)){
                result = false;
                System.out.println("중복된 아이디입니다" + result);
            }
            //데이터가 없어서 에러가 발생하면 try ~ catch로 잡아서 중복된 아이디가 아닌것을 확인후 result = true
        }catch (Exception e){
                result = true;
                System.out.println("가입가능한 아이디 입니다." + result);

        }
        /* Josn 방식으로 데이터를 만들고, [ result : value ] 로 만들어서 html로 넘겨줌
        이러면 ajax 스크립트가 동작하면서 result : value 를 인식하고 value 값에 따라서
        alert 를 생성함
         */
        JSONObject jso = new JSONObject();
        System.out.println("result: " + result);
        jso.put("result", result);

        //alert는 object를 띄울 수 없어서 따로 text로 변환해서 띄움
        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();
        out.print(jso.toString());
    }

    @RequestMapping(value = "/member/nickChk", method = {RequestMethod.POST})
    public void nickChk(HttpServletResponse res, HttpServletRequest req, Model model) throws IOException {
        //request 요청 파라미터로 넣어온 값 중 userid 를 가져와서 저장
        String nickname = req.getParameter("nickname");

        //아이디 중복 체크를 확인하기 위한 변수
        boolean result = false;
        Long id;

        try{
            System.out.println("userid: " + nickname);

            //findById 로 DB에 아이디가 저장되어있는지 여부 확인
            //만약 저장되어 있다면 chkID 값에 DB 에 있는 아이디가 찾아진 후 result = false
            //아니면 데이터를 찾을 수 없어 에러가 발생할 것임
            String chknick = memberRepository.findByNickname(nickname).getNickname();
            System.out.println("DB: " + chknick);
            if(chknick.equals(nickname)){
                result = false;
                System.out.println("중복된 닉네임입니다" + result);
            }
            //데이터가 없어서 에러가 발생하면 try ~ catch로 잡아서 중복된 아이디가 아닌것을 확인후 result = true
        }catch (Exception e){
            result = true;
            System.out.println("가입가능한 닉네임 입니다." + result);

        }
        /* Josn 방식으로 데이터를 만들고, [ result : value ] 로 만들어서 html로 넘겨줌
        이러면 ajax 스크립트가 동작하면서 result : value 를 인식하고 value 값에 따라서
        alert 를 생성함
         */
        JSONObject jso = new JSONObject();
        System.out.println("result: " + result);
        jso.put("result", result);

        //alert는 object를 띄울 수 없어서 따로 text로 변환해서 띄움
        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();
        out.print(jso.toString());
    }

    //전체조회
    @GetMapping(value = "/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/members/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Member member = memberService.get(id);
            if(id == member.getId()){
                memberService.deleteById(id);
            } else{
                memberService.get(id);
            }
            model.addAttribute("member", member);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "Login/Singup";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/members";
        }
    }

    @GetMapping("/members/delete/{id}")
    public String deleteMember(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            memberService.deleteById(id);
            ra.addFlashAttribute("message", "The member ID " + id + " has been deleted.");
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/members";
    }

}