package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.entity.Client;
import com.example.app.entity.Statuses;
import com.example.app.from.list;
import com.example.app.repository.ClientRepository;
import com.example.app.repository.StatusesRepository;
import com.example.app.service.ClientService;
import com.example.app.service.ListService;

@Controller
public class ListController {
	@Autowired
	 private ListService ListService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private StatusesRepository statusesRepository;
	@Autowired//オートワイヤリング設定(DIコンテナから型が一致するものを取り出しインジェクションする)
	 private ClientRepository clientRepository;

	@GetMapping("/list")
	public String list(Model model){
		List<Statuses> statuses = ListService.findAllStatuses();
		model.addAttribute("statuses", statuses);
		return "list";
	}


     @GetMapping("/customers")
     public String getClient(Model model,@RequestParam Integer id) {
    	 List<Client> clients = clientService.findAllClient();
    	 List<Client> client= new ArrayList<>();

    	 System.out.println(id);
    	 for(int i=0;i < clients.size();i++) {
          	if(clients.get(i).getStatus()==id) {
          		client.add(clients.get(i));
          	}
    	 }
    	 model.addAttribute("client", client);
    	 return "customers";
     }

     @RequestMapping(value = "/createlist", method = RequestMethod.POST)
 	public String createlist(Model model,@ModelAttribute list statuse) {
 		// フォームの中から名前と年齢を取得してデータベース登録処理へ
    	 System.out.println("hyuh"+statuse);
 		ListService.save(statuse.getStatuses());
 		List<Statuses> newestid = statusesRepository.findAll();

 		for(int i=0;i < statuse.getIds().size();i++) {
 			Client Client = clientRepository.findById(statuse.getIds().get(i)).get();

 			clientService.save(Client.getId(),Client.getName(),Client.getPostal_code(),Client.getPrefectures(),
 					Client.getStreet_address(),Client.getPhone_number(),Client.getPhone_number_sub(),Client.getIndustry(),
 					newestid.get(newestid.size()-1).getId());


 		}

	    model.addAttribute("newestid", newestid);

 		return "redirect:/";
     }


}

     //@GetMapping("/")
 	//public String index(Model model,@AuthenticationPrincipal User user){
 		//List<Client> client = clientService.findAllClient();
        // for(int i=0;i < client.size();i++) {
         	//if(user.getId().equals(client.get(i).getUser_id())&&2 == client.get(i).getStatus()) {
         		//model.addAttribute("client", client.get(i));

         	//}
        // }
 		//return "top";






