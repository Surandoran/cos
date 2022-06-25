package com.example.cosmetic.config;

import com.example.cosmetic.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //config Bean이라는 것을 명시해주는 어노테이션
@EnableWebSecurity // Spring Security config를 할 클래스라는 것을 명시
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter { //상속받아 필요한 메소드를 구현하여 필요한 설정을 함

//    private MemberService memberService;
//
//    //입력받은 비밀번호를 db에 바로 저장하는것이 아니라 암호화해서 저장
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // 인증을 무시하기 위한 설정
//        web.ignoring().antMatchers("/resources/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .and()
//            .formLogin()     // 로그인 설정
//                .loginPage("/Login/MemberLogin")      // 커스텀 login 페이지를 사용
//                .defaultSuccessUrl("/Main/home")      // 로그인 성공 시 이동할 페이지
//                .permitAll()
//                .and()
//            .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/Login/login"))
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true)    // 세션 초기화
//                .and()
//            .exceptionHandling();
//    }

    // 1. UserDetailsService를 자동주입 받은 뒤
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //super.configure(web); // 아무런 작업을 하지 않음

        // 스프링 시큐리티의 필터 연결을 설정하기 위한 오버라이딩이다.
        // 예외가 웹접근 URL를 설정한다.
        // ACL(Access Control List - 접근 제어 목록)의 예외 URL을 설정
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 인터셉터로 요청을 안전하게 보호하는 방법을 설정하기 위한 오버라이딩이다.
//        super.configure(http); // 모든 url 막고있음
        // 인터셉터로 요청을 안전하게 보호하는 방법을 설정하기 위한 오버라이딩이다.
        // 1. ACL 설정
        http.authorizeRequests()
//                .antMatchers("/Login/MemberLogin", "/user/logout").authenticated()
//                .antMatchers("/Mypage/**").authenticated()
//                .antMatchers("/members/**").hasAuthority("ROLE_ADMIN")
//                .anyRequest().permitAll();

                .antMatchers("/**").permitAll()
                .and();
        // Temporary for Testing 임시로 csrf 설정 막기
        http.csrf().disable();

        // 2. 로그인 설정
        http
                .formLogin()
                .loginPage("/Login/MemberLogin") 	// 로그인 페이지 url
                .loginProcessingUrl("/login")  // view form의 action과 맞아야함
                .failureUrl("/Login/MemberLogin?result=fail") // 로그인 실패시 redirect
                .defaultSuccessUrl("/", true) // 로그인 성공시
                .usernameParameter("name")  // 로그인 요청시 id용 파라미터 (메소드 이름이 usernameParameter로 무조건 써야하지만, 파라미터는 email이든 id이든 name이든 상관없다.)
                .passwordParameter("pw");	// 로그인 요청시 password용 파라미터

        // 3. 로그아웃 설정
        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/") // 로그아웃 성공시
                .invalidateHttpSession(true);
    }


    // 2. UserDetailService를 설정
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 사용자 세부 서비스를 설정하기 위한 오버라이딩이다.
        auth.userDetailsService(userDetailsService);
    }



}
