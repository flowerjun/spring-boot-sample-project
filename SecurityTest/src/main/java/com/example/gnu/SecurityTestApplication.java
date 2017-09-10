package com.example.gnu;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SecurityTestApplication {

	@Autowired
	static ApplicationContext ctx;
	public static void main(String[] args) throws URISyntaxException {
		SpringApplication.run(SecurityTestApplication.class, args);
	}
}
