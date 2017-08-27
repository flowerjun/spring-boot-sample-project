package com.example.gnu.SecurityTest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.example.gnu.Service.UserServiceImpl;

@Configuration
@EnableWebSecurity
@Profile("prod")
public class SecutiryConfigurerProd extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	public SecutiryConfigurerProd() {
		// TODO Auto-generated constructor stub
		System.out.println("Prod mode");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		super.configure(auth);
		auth.userDetailsService(userServiceImpl);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
		web.ignoring().antMatchers("/h2-console/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(
				"/hello",
				"/f"
				).permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/loginform.html").permitAll();
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}	
}
