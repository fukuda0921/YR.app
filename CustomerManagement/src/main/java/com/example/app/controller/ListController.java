package com.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.entity.Statuses;
import com.example.app.service.ListService;

@Controller
public class ListController {
	@Autowired
	 private ListService ListService;
	@GetMapping("/list")
	public String list(Model model){
		List<Statuses> statuses = ListService.findAllStatuses();
		model.addAttribute("statuses", statuses);
		return "list";
	}


}