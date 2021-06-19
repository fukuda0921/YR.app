package com.example.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.entity.Client;
import com.example.app.entity.Industry;
import com.example.app.entity.Prefectures;
import com.example.app.entity.Statuses;
import com.example.app.repository.ClientCustomRepository;
import com.example.app.repository.ClientRepository;
import com.example.app.repository.IndustryRepository;
import com.example.app.repository.PrefecturesRepository;
import com.example.app.repository.StatusesRepository;

@Service
public class ClientService {
	 @Autowired//オートワイヤリング設定(DIコンテナから型が一致するものを取り出しインジェクションする)
	 private ClientRepository clientRepository;
	 @Autowired//オートワイヤリング設定(DIコンテナから型が一致するものを取り出しインジェクションする)
	 private PrefecturesRepository prefecturesRepository;
	 @Autowired//オートワイヤリング設定(DIコンテナから型が一致するものを取り出しインジェクションする)
	 private IndustryRepository industryRepository;
	 @Autowired//オートワイヤリング設定(DIコンテナから型が一致するものを取り出しインジェクションする)
	 private ClientCustomRepository clientCustomRepository;
	 @Autowired//オートワイヤリング設定(DIコンテナから型が一致するものを取り出しインジェクションする)
	 private StatusesRepository statusesRepository;


    public void save(String name,String postal_code, Integer prefectures,String street_address,
    		         String phone_number,String phone_number_sub, Integer industry) {
    	Client client = new Client();
    	client.setName(name);
    	client.setPostal_code(postal_code);
    	client.setPrefectures(prefectures);
    	client.setStreet_address(street_address);
    	client.setPhone_number(phone_number);
    	client.setPhone_number_sub(phone_number_sub);
    	client.setIndustry(industry);
    	client.setStatus(1);
    	clientRepository.save(client);
	}

	public List<Client> findAllClient() {
		return clientRepository.findAll();
	}

	public List<Prefectures> findAllPrefectures() {
		return prefecturesRepository.findAll();
	}

	public List<Industry> findAllIndustry() {
		return industryRepository.findAll();
	}

	public List<Statuses> findAllStatuses() {
		return statusesRepository.findAll();
	}

	public List<Client> search(String name, String phone_number, Integer industry, Integer prefectures,
			Integer status) {
		List<Client> result;
		if ("".equals(name) && "".equals(phone_number) && industry == 0 && prefectures == 0 && status == 0) {
			result = clientRepository.findAll();
		} else {
			result = clientCustomRepository.search(name, phone_number, industry, prefectures,status);
		}
		return result;
	}

	public void save(Integer id,String name,String postal_code, Integer prefectures,String street_address,
	         String phone_number,String phone_number_sub, Integer industry,Integer status,Integer user_id,Date now
	         ,Date next_call_day,String remarks) {
		Client client = new Client();
		client.setId(id);
		client.setName(name);
		client.setPostal_code(postal_code);
		client.setPrefectures(prefectures);
		client.setStreet_address(street_address);
		client.setPhone_number(phone_number);
		client.setPhone_number_sub(phone_number_sub);
		client.setIndustry(industry);
		client.setStatus(status);
		client.setUser_id(user_id);
		client.setNow(now);
		client.setNext_call_day(next_call_day);
		client.setRemarks(remarks);
		clientRepository.save(client);
		}
}
