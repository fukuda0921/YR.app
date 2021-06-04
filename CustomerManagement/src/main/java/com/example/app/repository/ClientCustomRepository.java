package com.example.app.repository;


import java.util.List;

import com.example.app.entity.Client;


public interface ClientCustomRepository{

	public List<Client> search(String name, String phone_number, Integer industry, Integer prefectures);

}




