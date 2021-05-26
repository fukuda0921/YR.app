package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.app.from.signup;
import com.example.app.service.UserDetailsServiceImpl;


@Controller

public class UserController {
	@Autowired
	UserDetailsServiceImpl UserDetailsServiceImpl;

	@Autowired
    PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String login(){
		return "login";
	}
	@GetMapping("/")
	public String index(){
		return "top";
	}

	@GetMapping("/signup")
	public String signup(){
		return "signup";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute signup signup) {
		// フォームの中から名前と年齢を取得してデータベース登録処理へ
		String encodedPassword = passwordEncoder.encode(signup.getPassword());
		UserDetailsServiceImpl.save(signup.getUsername(), encodedPassword);
		return "redirect:/";
	}


}


