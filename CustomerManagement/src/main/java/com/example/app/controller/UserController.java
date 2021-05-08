package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	@GetMapping("/sample")
	public String index(){
		return "sample";
	}
}