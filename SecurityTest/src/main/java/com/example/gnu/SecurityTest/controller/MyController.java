package com.example.gnu.SecurityTest.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gnu.DAO.UserDAO;

@Controller
public class MyController {
	Logger logger = LogManager.getLogger();
	@Autowired
	UserDAO userDao;
	@GetMapping("/hello")
	public String hello(String name, Model model){
		model.addAttribute("name", name);
		return "Hello";
	}
	
	@GetMapping("/loginme")
	public @ResponseBody String login(String id, String password){
		return userDao.selectUserById(id, password);
	}
	@GetMapping("/f")
	public @ResponseBody void filterTest(){
		System.out.println("filter");
	}	
}
