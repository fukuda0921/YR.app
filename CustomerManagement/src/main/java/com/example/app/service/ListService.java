package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.entity.Statuses;
import com.example.app.repository.StatusesRepository;

@Service
public class ListService {
	@Autowired
	private StatusesRepository statusesRepository;


	public List<Statuses> findAllStatuses() {
		return statusesRepository.findAll();
	}
	public void save(String statuses) {
     Statuses statuse  = new Statuses();
     statuse.setStatuses(statuses);
     System.out.println(statuses);
      statusesRepository.save(statuse);
	}




}
