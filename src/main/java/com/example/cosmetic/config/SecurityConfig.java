package com.example.cosmetic.config;

import com.example.cosmetic.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration //config Bean이라는 것을 명시해주는 어노테이션
@EnableWebSecurity // Spring Security config를 할 클래스라는 것을 명시
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter { //상속받아 필요한 메소드를 구현하여 필요한 설정을 함

    private MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
        web
                .ignoring()
                .antMatchers(Constants.STATIC_RESOURCES_URL_PATTERS)
                .antMatchers(HttpMethod.GET, "/exception/**");
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/Mypage/**").hasRole("MEMBER")
                .antMatchers("/**").permitAll()
                .antMatchers("/board/**").permitAll()
                .antMatchers("/file-download/**").permitAll()            //파일 다운로드
                .antMatchers("/Login/**").permitAll()					    //로그인, 회원가입 접속허용
                .antMatchers("/resource/**/images/**").permitAll()		//이미지
                .anyRequest().authenticated() //인증이 되어야함
                .and() // 로그인 설정
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                //세션관리
                .sessionManagement(s -> s
                .maximumSessions(200) 				//세션 허용 갯수
//                .expiredUrl(Url.AUTH.LOGIN)		 	//세션 만료시 이동할 페이지
                .sessionRegistry(sesionRegistry())
                .maxSessionsPreventsLogin(false));	//동시 로그인 차단, false인 경우 기존 세션 만료
                // 403 예외처리 핸들링
//                .exceptionHandling().accessDeniedPage("/user/denied");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SessionRegistry sesionRegistry() {
        return new SpringSecuritySessionRegistImpl();
    }


    /* 관리자 아이디 파라미터 이름 */
    public static final String USERNAME_PARAM = "admin";

    /* 관리자 비밀번호 파라미터 이름 */
    public static final String PASSWORD_PARAM = "1111";

}
