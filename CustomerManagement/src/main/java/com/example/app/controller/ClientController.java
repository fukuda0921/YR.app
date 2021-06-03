package com.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.app.entity.Client;
import com.example.app.entity.Industry;
import com.example.app.entity.Prefectures;
import com.example.app.from.client;
import com.example.app.service.ClientService;
@Controller
public class ClientController {

	@Autowired//オートワイヤリング設定(DIコンテナから型が一致するものを取り出しインジェクションする)
	 private ClientService clientService;

	@GetMapping("/createclient")
	public String index(Model model){
		List<Prefectures> prefectures = clientService.findAllPrefectures();
		model.addAttribute("prefectures", prefectures);
		List<Industry> industry  = clientService.findAllIndustry();
		model.addAttribute("industry", industry);
		return "createclient";
	}
	@GetMapping("/customerlist")
	public String customerlist(Model model){
		List<Prefectures> prefectures = clientService.findAllPrefectures();
		model.addAttribute("prefectures", prefectures);
		System.out.println(prefectures);
		List<Industry> industry  = clientService.findAllIndustry();
		model.addAttribute("industry", industry);
		System.out.println(industry);
		List<Client> customerlist = clientService.findAllCustomrelist();
		System.out.println(customerlist);
		model.addAttribute("customerlist",customerlist);
		return "customerlist";
	}


	@RequestMapping(value = "/postclient", method = RequestMethod.POST)
	public String signup(@ModelAttribute client client) {
		// フォームの中から名前と年齢を取得してデータベース登録処理へ
		clientService.save(client.getName(),client.getPostal_code(),client.getPrefectures(),
						   client.getStreet_address(),client.getPhone_number(),client.getPhone_number_sub(),
						   client.getIndustry());
		return "redirect:/";
	}
}
