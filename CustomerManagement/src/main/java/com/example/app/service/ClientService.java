package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.entity.Client;
import com.example.app.entity.Industry;
import com.example.app.entity.Prefectures;
import com.example.app.repository.ClientCustomRepository;
import com.example.app.repository.ClientRepository;
import com.example.app.repository.IndustryRepository;
import com.example.app.repository.PrefecturesRepository;

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
    	clientRepository.save(client);
	}

	public List<Prefectures> findAllPrefectures() {
		return prefecturesRepository.findAll();
	}

	public List<Industry> findAllIndustry() {
		return industryRepository.findAll();
	}

	public List<Client> search(String name, String phone_number, Integer industry, Integer prefectures) {
		List<Client> result;
		if ("".equals(name) && "".equals(phone_number) && industry == 1 && prefectures == 1) {
			result = clientRepository.findAll();
		} else {
			result = clientCustomRepository.search(name, phone_number, industry, prefectures);
		}
		return result;
	}
}
