package com.example.gnu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	Logger logger = LogManager.getLogger();
	@Autowired
	ApplicationContext context;
	@GetMapping("/hello")
	public String hello(String name, Model model){
		model.addAttribute("name", name);
		return "Hello";
	}
	
	@GetMapping("/filtered")
	public @ResponseBody void f(){
		System.out.println(context.getBean("filterClass"));
		System.out.println(context.getBean("exampleFilter"));
		System.out.println("filtered");
	}
	
	@GetMapping("/nofilter")
	public @ResponseBody void n(){
		System.out.println("no filter");
	}
	
	@GetMapping("/loginme")
	public String login(@RequestParam(defaultValue = "false") String error, @RequestParam(defaultValue = "false") String logout, Model model){
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return "loginForm";
	}
	
	@GetMapping("/success")
	public String successPage(HttpServletRequest req, Model model){
		return "Success";
	}
	
	@GetMapping("/admin")
	public String adminPage(HttpServletRequest req, Model model){
		return "Admin";
	}
	@GetMapping("/checkRole")
	public @ResponseBody Map<String, String> checkRole(Authentication auth){
		Map<String, String> result = new HashMap<String, String>();
		result.put("name", auth.getName());
		System.out.println(auth.getPrincipal());
		result.put("authority", auth.getAuthorities().toString());
		return result;
	}
	
}
