package com.example.gnu.SecurityTest.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("security");
		http.antMatcher("/**/*.js").addFilterBefore(new Filter() {
			
			@Override
			public void init(FilterConfig arg0) throws ServletException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
					throws IOException, ServletException {
				// TODO Auto-generated method stub
				System.out.println("js filter");
				HttpServletRequest req = (HttpServletRequest)arg0;
				HttpServletResponse res = (HttpServletResponse)arg1;
				System.out.println(req.getRequestURI());
				res.sendRedirect("http://localhost:6799"+req.getRequestURI());
			}
			
			@Override
			public void destroy() {
				// TODO Auto-generated method stub
				
			}
		}, UsernamePasswordAuthenticationFilter.class);
		http.authorizeRequests().antMatchers("/h2-console/**","/metrics/**","metrics.json").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/loginform.html").permitAll().and().logout().permitAll();
		// http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		http.csrf().disable();
	}	
}
