package com.example.gnu.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.gnu.DAO.UserDAO;
import com.example.gnu.DTO.MyUser;

@Configuration
@EnableWebSecurity
@Profile("prod")
public class SecutiryConfigurerProd extends WebSecurityConfigurerAdapter{
	@Autowired
	DataSource datasource;
	@Autowired
	UserDAO userDao;
	
	private final String[] ALLOW_LIST = new String[]{
			"/h2-console/**", "/loginme", "/logout", "/error/**", "/loginCheck"};
	
	public SecutiryConfigurerProd() {
		// TODO Auto-generated constructor stub
		System.out.println("Prod mode");
	}
	@SuppressWarnings("unused")
	private void inMemoryAuthBuilder(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}
	@SuppressWarnings("unused")
	private void jdbcAuthBuilder(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(datasource)
		.usersByUsernameQuery("SELECT USERID, PASSWORD,ENABLED "
				+ "FROM USERLIST "
				+ "WHERE USERID=?")
		.authoritiesByUsernameQuery("SELECT USERID, AUTHORITY "
				+ "FROM USERLIST "
				+ "WHERE USERID=?");
	}
	
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		super.configure(auth);
		// inMemoryAuthBuilder(auth);
		// jdbcAuthBuilder(auth);
		auth.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
				MyUser user = userDao.findUserByUsername(arg0);
				if(null == user){
					throw new UsernameNotFoundException("User not found");
				} else {
					String authority = userDao.findAuthorityByUsername(arg0);
					user.addAuthorities(authority);
					return user;
				}
			}
		});
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 여기에 설정된 놈들은 모든 보안 설정에 영향을 받음 
		super.configure(web);
		web.ignoring().antMatchers("/h2-console/**","/tld/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { 	// 리소스 레벨 보안, namespace 구분의 용도로 활용
		http.authorizeRequests().antMatchers(ALLOW_LIST).permitAll()
		.antMatchers("/admin/**").hasAuthority("ADMIN")
		.anyRequest().authenticated() // 권한이 있으면 다른 페이지들도 접근 가능
		.and().formLogin().loginPage("/loginme")
		.loginProcessingUrl("/loginCheck")
		.defaultSuccessUrl("/success",true).failureUrl("/loginme?error=true")
		.usernameParameter("username")
		.passwordParameter("password")
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
		.invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/loginme?logout=true")
		.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}	
}
