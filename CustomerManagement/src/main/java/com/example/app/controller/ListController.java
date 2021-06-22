package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.entity.Client;
import com.example.app.entity.Statuses;
import com.example.app.entity.User;
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
	public String list(Model model,@AuthenticationPrincipal User user){
		List<Client> client = clientService.findAllClient();
		List<Client> clients= new ArrayList<>();
        for(int i=0;i < client.size();i++) {
        	if(user.getId().equals(client.get(i).getUser_id())&&2 == client.get(i).getStatus()) {
        		clients.add(client.get(i));
        	}
        }
        model.addAttribute("client", clients);
		List<Statuses> statuses = ListService.findAllStatuses();
		model.addAttribute("statuses", statuses);
		return "listBetu";
	}


     @GetMapping("/customers")
     public String getClient(Model model,@RequestParam Integer id,@AuthenticationPrincipal User user) {
    	 List<Client> clientss = clientService.findAllClient();
 		List<Client> clientsss= new ArrayList<>();
         for(int i=0;i < clientss.size();i++) {
         	if(user.getId().equals(clientss.get(i).getUser_id())&&2 == clientss.get(i).getStatus()) {
         		clientsss.add(clientss.get(i));
         	}
         }
         model.addAttribute("clientss", clientsss);
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
 		ListService.save(statuse.getStatuses());
 		List<Statuses> newestid = statusesRepository.findAll();

 		for(int i=0;i < statuse.getIds().size();i++) {
 			Client Client = clientRepository.findById(statuse.getIds().get(i)).get();

 			clientService.save(Client.getId(),Client.getName(),Client.getPostal_code(),Client.getPrefectures(),
 					Client.getStreet_address(),Client.getPhone_number(),Client.getPhone_number_sub(),Client.getIndustry(),
 					newestid.get(newestid.size()-1).getId(),Client.getUser_id(),Client.getNow(),Client.getNext_call_day(),
 					Client.getRemarks());


 		}

	    model.addAttribute("newestid", newestid);

 		return "redirect:/";
     }
//     clientService.save(client.getId(),client.getName(),client.getPostal_code(),client.getPrefectures(),
//			   client.getStreet_address(),client.getPhone_number(),client.getPhone_number_sub(),
//			   client.getIndustry(),client.getStatus(),user.getId(),now,client.getNext_call_day()
//			   ,client.getRemarks());


}







