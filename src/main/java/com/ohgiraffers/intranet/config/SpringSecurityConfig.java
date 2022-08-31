package com.ohgiraffers.intranet.config;

import com.ohgiraffers.intranet.member.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private MemberService memberService;

    public SpringSecurityConfig(MemberService memberService){

        this.memberService = memberService;
    }

    /* 암호화 설정*/
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    /* 시큐리티 설정 무시할 리소스 등록. 여러분 작업 화이팅 */
    public void configure(WebSecurity web){

        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/lib/**");
    }

    /* HTTP 요청에 대한 권한 설정 코드 */
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().disable()
                .authorizeRequests() //요청에 대한 권한 체크
                .anyRequest().permitAll()
                // 추후 업로드 예정입니다 08/30 19시 35분.

           .and()
                .formLogin() // 로그인 form을 따로 이용하여 로그인.
                .loginPage("/member/login")
                .successForwardUrl("/")

           .and()
                .logout() //로그아웃
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .deleteCookies("JSESSIONID") // 쿠키 제거 -> 로그아웃 시 자동로그인이 해제되도록 할 생각.
                .invalidateHttpSession(true);

        //중복 로그인 방지용 코드
    http.sessionManagement()
            .maximumSessions(1) //세션 최대 허용 수
            .maxSessionsPreventsLogin(false) //중복 로그인을 인지하면 이전 로그인이 풀림

//           .and()
//                .exceptionHandling()
//                .accessDeniedPage("/common/denied")
;    }

    /* 권한 등록 시 인증할 비즈니스 로직 등록 코드 */
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }


}
