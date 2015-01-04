package com.tbelina.spring.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "redirect:j_spring_security_logout";
	}
	
	@RequestMapping("/heartbeat")
	public @ResponseBody String heartbeat(HttpSession session) {
		return Integer.toString(session.getMaxInactiveInterval());
	}
	
}
