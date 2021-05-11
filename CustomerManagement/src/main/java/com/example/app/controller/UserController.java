package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.from.signup;
import com.example.app.service.UserDetailsServiceImpl;


@Controller

public class UserController {
	@Autowired
	UserDetailsServiceImpl UserDetailsServiceImpl;

	@GetMapping("/login")
	public String login(){
		return "login";
	}
	@GetMapping("/")
	public String index(){
		return "sample";
	}
	@GetMapping("/signup")
	public String signup(){
		return "signup";
	}
	@PostMapping("signups")
	public String signup(@ModelAttribute signup signup) {
		// フォームの中から名前と年齢を取得してデータベース登録処理へ
		UserDetailsServiceImpl.save(signup.getUsername(), signup.getPassword());
		return "redirect:/";
	}

}