package com.ohgiraffers.intranet.config;

import com.ohgiraffers.intranet.member.service.AuthenticationService;
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
                .antMatchers("/", "/common/**").permitAll()
                // notice 게시판 접근 권한
//                .antMatchers("/notice/news/list/**", "/notice/gallery/**")
//                .hasAnyAuthority("ROLE_NT_ALL", "ROLE_ALL_ALL")
                // calendar 게시판 접근 권한
//                .antMatchers("/calendar/main/**")
//                .hasAnyAuthority("ROLE_CD_DEPT", "ROLE_CD_ALL", "ROLE_ALL_ALL")
                // emplManage 게시판 접근 권한
                .antMatchers("/member/regist/**")
                .hasAnyAuthority("ROLE_NM")
                .anyRequest().authenticated()
               // sign 게시판 접근 권한
               // board 게시판 접근 권한
               // authorManage 게시판 접근 권한

                // 추후 업로드 예정입니다 08/30 19시 35분.

           .and()
                .formLogin() // 로그인 form을 따로 이용하여 로그인.
                .loginPage("/common/login")
                .usernameParameter("mem_id") //html id name을 mem_id로 쓰겠다는 설정.
                .passwordParameter("mem_pw") //html pw name을 mem_pw로 사용하겠다는 설정.
                .defaultSuccessUrl("/main/main")
                .failureUrl("/member/loginFail")

           .and()
                .logout() //로그아웃
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .deleteCookies("JSESSIONID") // 쿠키 제거
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/");

        //중복 로그인 방지용 코드
    http.sessionManagement()
            .maximumSessions(1) //세션 최대 허용 수
            .maxSessionsPreventsLogin(false); //중복 로그인을 인지하면 이전 로그인이 풀림 -> 타 브라우저 환경에서 실행 됐음.

                //예외처리 핸들링
               http.exceptionHandling()
                    .accessDeniedPage("/common/denied.html");

;    }



}
