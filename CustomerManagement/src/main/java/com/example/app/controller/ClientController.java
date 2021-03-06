package com.example.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.example.app.entity.User;
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
	public String index(Model model,@AuthenticationPrincipal User user){
		List<Prefectures> prefectures = clientService.findAllPrefectures();
		model.addAttribute("prefectures", prefectures);
		List<Industry> industry  = clientService.findAllIndustry();
		model.addAttribute("industry", industry);
		List<Client> client = clientService.findAllClient();
		List<Client> clients= new ArrayList<>();
        for(int i=0;i < client.size();i++) {
        	if(user.getId().equals(client.get(i).getUser_id())&&2 == client.get(i).getStatus()) {
        		clients.add(client.get(i));
        	}
        }
        model.addAttribute("client", clients);
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
	public String search(Model model,@AuthenticationPrincipal User user){
		List<Prefectures> prefectures = clientService.findAllPrefectures();
		model.addAttribute("prefectures", prefectures);
		List<Industry> industry  = clientService.findAllIndustry();
		model.addAttribute("industry", industry);
		List<Statuses> statuses  = clientService.findAllStatuses();
		model.addAttribute("statuses", statuses);
		List<Client> client = clientService.findAllClient();
		List<Client> clients= new ArrayList<>();
        for(int i=0;i < client.size();i++) {
        	if(user.getId().equals(client.get(i).getUser_id())&&2 == client.get(i).getStatus()) {
        		clients.add(client.get(i));
        	}
        }
        model.addAttribute("client", clients);
		return "clientsearch";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@ModelAttribute client client,Model model,@AuthenticationPrincipal User user) {
		// フォームの中から名前と年齢を取得してデータベース登録
		System.out.println("hello"+client);
		List<Client> result = clientService.search(client.getName(),client.getPhone_number(),client.getIndustry(),
						   client.getPrefectures(),client.getStatus(),client.getLocal1(),client.getLocal2());

		model.addAttribute("result", result);
		List<Prefectures> prefectures = clientService.findAllPrefectures();
		model.addAttribute("prefectures", prefectures);
		List<Industry> industry  = clientService.findAllIndustry();
		model.addAttribute("industry", industry);
		List<Statuses> statuses  = clientService.findAllStatuses();
		model.addAttribute("statuses", statuses);
		List<Client> clientss = clientService.findAllClient();
		List<Client> clients= new ArrayList<>();
        for(int i=0;i < clientss.size();i++) {
        	if(user.getId().equals(clientss.get(i).getUser_id())&&2 == clientss.get(i).getStatus()) {
        		clients.add(clientss.get(i));
        	}
        }
        model.addAttribute("client", clients);
		return "clientsearch";
	}

	@GetMapping("edit")
	  String edit(@ModelAttribute client client,Model model,@AuthenticationPrincipal User user) {
		Client Client = clientRepository.findById(client.getId()).get();
	    model.addAttribute("client", Client);
	    System.out.println(Client);
		List<Prefectures> prefectures = clientService.findAllPrefectures();
		model.addAttribute("prefectures", prefectures);
		List<Industry> industry  = clientService.findAllIndustry();
		model.addAttribute("industry", industry);
		List<Statuses> statuses  = clientService.findAllStatuses();
		model.addAttribute("statuses", statuses);
		List<Client> clientss = clientService.findAllClient();
		List<Client> clients= new ArrayList<>();
        for(int i=0;i < clientss.size();i++) {
        	if(user.getId().equals(clientss.get(i).getUser_id())&&2 == clientss.get(i).getStatus()) {
        		clients.add(clientss.get(i));
        	}
        }
        model.addAttribute("clients", clients);
	    return "edit";
	  }

	  @RequestMapping(value = "/postupdate", method = RequestMethod.POST)
	  String update(@ModelAttribute client client,@AuthenticationPrincipal User user) {
		  Date now = new Date();
		  System.out.println("こんにちわ" + client.getNext_call_day());
		clientService.save(client.getId(),client.getName(),client.getPostal_code(),client.getPrefectures(),
				   client.getStreet_address(),client.getPhone_number(),client.getPhone_number_sub(),
				   client.getIndustry(),client.getStatus(),user.getId(),now,client.getNext_call_day()
				   ,client.getRemarks());
	    return "redirect:/";
	  }
}
