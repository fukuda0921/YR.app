package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.app.entity.Client;
import com.example.app.entity.User;
import com.example.app.from.signup;
import com.example.app.service.ClientService;
import com.example.app.service.UserDetailsServiceImpl;


@Controller

public class UserController {
	@Autowired
	UserDetailsServiceImpl UserDetailsServiceImpl;

	@Autowired
    PasswordEncoder passwordEncoder;

	@Autowired//オートワイヤリング設定(DIコンテナから型が一致するものを取り出しインジェクションする)
	 private ClientService clientService;

	@GetMapping("/login")
	public String login(){
		return "login";
	}

	@GetMapping("/")
	public String index(Model model,@AuthenticationPrincipal User user){
		List<Client> client = clientService.findAllClient();
		List<Client> clients= new ArrayList<>();
        for(int i=0;i < client.size();i++) {
        	if(user.getId().equals(client.get(i).getUser_id())&&2 == client.get(i).getStatus()) {
        		clients.add(client.get(i));
        	}
        }
        model.addAttribute("client", clients);
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


