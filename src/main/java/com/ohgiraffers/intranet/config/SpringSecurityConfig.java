package com.ohgiraffers.intranet.config;

import com.ohgiraffers.intranet.member.service.AuthenticationService;
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

    private AuthenticationService authenticationService;

    public SpringSecurityConfig(AuthenticationService authenticationService){

        this.authenticationService = authenticationService;
    }

    /* 암호화 설정*/
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    /* 권한 등록 시 인증할 비즈니스 로직 등록 코드
     * 회원 정보를 가져와 있는 회원인지 아닌지 체크 */
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
    }


    /* 시큐리티 설정 무시할 리소스 등록. 여러분 작업 화이팅 */
    public void configure(WebSecurity web) throws Exception{

        web.ignoring().antMatchers("/css/**", "/images/**", "/js/**", "/lib/**");
    }

    /* HTTP 요청에 대한 권한 설정 코드 */
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().disable()//non-browser clients 만을 위한 서비스하면 csrf 를 disable 하여도 좋다고 함, 서버에 인증정보를 저장하지 않기 때문
                .authorizeRequests() //요청에 대한 권한 체크
                .antMatchers("/calender/**").authenticated()
                .antMatchers("/calender/**").hasRole("CD_ALL")
                .antMatchers("/calender/**").hasRole("CD_DEPT")
                .antMatchers("/calender/**").hasRole("ALL_ALL")
                .mvcMatchers("/emplManage/**").hasAnyAuthority("EM_READ","EM_ALL","ALL_ALL")
                .mvcMatchers("/notice/**").hasRole("NT_ALL")
                .mvcMatchers("/notice/**").hasRole("ALL_ALL")
//                .mvcMatchers("/notice/**","/msBoard/**","/sign/**","/board/**", "/calender/**").hasAnyAuthority("ALL_ALL")

                .anyRequest().permitAll()
                // 추후 업로드 예정입니다 08/30 19시 35분.

           .and()
                .formLogin() // 로그인 form을 따로 이용하여 로그인.
                .loginPage("/common/login")
                .usernameParameter("mem_id") //html id name을 mem_id로 쓰겠다는 코드.
                .passwordParameter("mem_pw") //html pw name을 mem_pw로 사용하겠다는 코드.
                .defaultSuccessUrl("/main/main")
                .failureUrl("/member/loginFail")

           .and()
                .logout() //로그아웃
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .deleteCookies("JSESSIONID") // 쿠키 제거 -> 로그아웃 시 자동로그인이 해제되도록 할 생각.
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/");

        //중복 로그인 방지용 코드
    http.sessionManagement()
            .maximumSessions(1) //세션 최대 허용 수
            .maxSessionsPreventsLogin(false); //중복 로그인을 인지하면 이전 로그인이 풀림

                //자동 로그인 코드
        http.rememberMe()
                    .key("heechang!") // token 생성 값. 필수
                    .rememberMeParameter("remember-me") // check-box 의 name과 맞추어야.
                    .tokenValiditySeconds(86400) // 쿠키의 만료 시간 24시간. * 2 를 붙일경우 이틀, * 30 하면 한달.
                    .alwaysRemember(false) // 사용자가 체크박스를 활성화 하지 않아도 항상 실행 방지.
                    .userDetailsService(userDetailsService()) // 사용자 정보를 받음.

                //403 예외처리 핸들링
               .and()
                    .exceptionHandling()
                    .accessDeniedPage("/member/loginFail")
;    }



}
