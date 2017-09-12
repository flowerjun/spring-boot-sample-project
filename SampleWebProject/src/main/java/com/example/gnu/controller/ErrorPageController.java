package com.example.gnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorPageController {
	@RequestMapping("/{errorCode}")
	public String errorPage(@PathVariable(required=true, name="errorCode") String errorCode, Model model){
		model.addAttribute("code", errorCode);
		return "errorPage/error";
	}
}
