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
import com.example.app.entity.Statuses;
import com.example.app.from.client;
import com.example.app.repository.ClientRepository;
import com.example.app.service.ClientService;

@Controller
public class ClientController {

	@Autowired//オートワイヤリング設定(DIコンテナから型が一致するものを取り出しインジェクションする)
	 private ClientService clientService;

	 @Autowired//オートワイヤリング設定(DIコンテナから型が一致するものを取り出しインジェクションする)
	 private ClientRepository clientRepository;

	@GetMapping("/createclient")
	public String index(Model model){
		List<Prefectures> prefectures = clientService.findAllPrefectures();
		model.addAttribute("prefectures", prefectures);
		List<Industry> industry  = clientService.findAllIndustry();
		model.addAttribute("industry", industry);
		return "createclient";
	}

	@RequestMapping(value = "/postclient", method = RequestMethod.POST)
	public String signup(@ModelAttribute client client) {
		// フォームの中から名前と年齢を取得してデータベース登録処理へ
		clientService.save(client.getName(),client.getPostal_code(),client.getPrefectures(),
						   client.getStreet_address(),client.getPhone_number(),client.getPhone_number_sub(),
						   client.getIndustry());
		return "redirect:/";
	}

	@GetMapping("/createclient/search")
	public String search(Model model){
		List<Prefectures> prefectures = clientService.findAllPrefectures();
		model.addAttribute("prefectures", prefectures);
		List<Industry> industry  = clientService.findAllIndustry();
		model.addAttribute("industry", industry);
		List<Statuses> statuses  = clientService.findAllStatuses();
		model.addAttribute("statuses", statuses);
		return "clientsearch";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@ModelAttribute client client,Model model) {
		// フォームの中から名前と年齢を取得してデータベース登録
		List<Client> result = clientService.search(client.getName(),client.getPhone_number(),client.getIndustry(),
						   client.getPrefectures());
		model.addAttribute("result", result);
		List<Prefectures> prefectures = clientService.findAllPrefectures();
		model.addAttribute("prefectures", prefectures);
		List<Industry> industry  = clientService.findAllIndustry();
		model.addAttribute("industry", industry);
		return "clientsearch";
	}

	@GetMapping("edit")
	  String edit(@ModelAttribute client client,Model model) {
		Client Client = clientRepository.findById(client.getId()).get();
	    model.addAttribute("client", Client);
		List<Prefectures> prefectures = clientService.findAllPrefectures();
		model.addAttribute("prefectures", prefectures);
		List<Industry> industry  = clientService.findAllIndustry();
		model.addAttribute("industry", industry);
		List<Statuses> statuses  = clientService.findAllStatuses();
		model.addAttribute("statuses", statuses);
	    return "edit";
	  }

	  @RequestMapping(value = "/postupdate", method = RequestMethod.POST)
	  String update(@ModelAttribute client client) {
		clientService.save(client.getId(),client.getName(),client.getPostal_code(),client.getPrefectures(),
				   client.getStreet_address(),client.getPhone_number(),client.getPhone_number_sub(),
				   client.getIndustry(),client.getStatus());
	    return "redirect:/";
	  }
}
