package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

// 3개 어노테이션이 셋트!
@Configuration		// 빈등록
@EnableWebSecurity	// 시큐리티 필터가 등록이 된다. 
@EnableGlobalMethodSecurity(prePostEnabled = true)	// 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean	// IoC 등록
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인 해주는데, password를 가로채기하는데 해당 password가 뭘로 해쉬가 되어 있는지 알아야 같은 해쉬로 암호화해서 DB에 있는 비밀번호와 비교 할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(principalDetailService)		// 어떻게 로그인을 진행 할 건지 설정 안넣으면 패스워드 비교를 못함.
			.passwordEncoder(encodePWD())	// 암호화가 어떤 방식인지 설정, 패스워드 비교 처리 후 스프링세션에 PrincipalDetail 객체 저장!
			;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()	// csrf 토큰 비활성화(테스트시 걸어두는게 좋음)
	    	.authorizeRequests()
	    		.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")	// URL /auth 하위에 요청에 대해서 
	    		.permitAll()                // 모두 들어올 수 있다.          
	    		.anyRequest()               // 그외 요청에 대해서            
	    		.authenticated()            // 인증이 되어야 한다.
    		.and()
    			.formLogin()
    			.loginPage("/auth/loginForm")	// 로그인 페이지 요청 URL 설정	
    			.loginProcessingUrl("/auth/loginProc")	// 스프링 시큐리티가 해당 주소로 요청하는 로그인을 가로채서 대신 로그인 처리를 진행한다.
    			.defaultSuccessUrl("/")	// 로그인 성공 시 '/' URL로 포워딩한다.
//    			.failureUrl("/fail");	// 실패 시 URL 처리
	    		;
	}
}
