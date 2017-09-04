package com.example.gnu.config;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfigurer {
	
	@Bean
	public FilterRegistrationBean exampleFilter(){
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new Filter() {
			
			@Override
			public void init(FilterConfig arg0) throws ServletException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
					throws IOException, ServletException {
				// TODO Auto-generated method stub
				String error = arg0.getParameter("error");
				if(null != error && error.equals("true")){
					System.out.println("Login fail");
				}
				arg2.doFilter(arg0, arg1);
			}
			
			@Override
			public void destroy() {
				// TODO Auto-generated method stub
				
			}
		});
		bean.setDispatcherTypes(DispatcherType.REQUEST);
		bean.addUrlPatterns("/loginme");
		return bean;
	}
}
