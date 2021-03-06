package com.example.app.repository;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.app.entity.Client;

@Component
public class ClientCustomRepositoryImpl implements ClientCustomRepository {

	@Autowired
	EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> search(String name, String phone_number, Integer industry_id, Integer prefectures
			,Integer status,Date local1,Date local2) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT g From Client g WHERE ");
		boolean andFlg = false;
		boolean nameFlg = false;
		boolean phone_numberFlg = false;
		boolean industryFlg = false;
		boolean prefecturesFlg = false;
		boolean statusFlg = false;
		boolean local1Flg = false;
		boolean local2Flg = false;
		if (!"".equals(name) && name != null) {
			sql.append(" g.name LIKE :name ");
			nameFlg = true;
			andFlg = true;
		}
		if (!"".equals(phone_number) && phone_number != null) {
			if (andFlg) sql.append(" AND ");
			sql.append("g.phone_number LIKE :phone_number ");
			phone_numberFlg = true;
			andFlg = true;
		}

		if (industry_id != 0) {
			if (andFlg) sql.append(" AND ");
			sql.append("g.industry = :industry_id ");
			industryFlg = true;
			andFlg = true;
		}
		if (prefectures != 0) {
			if (andFlg) sql.append(" AND ");
			sql.append("g.prefectures = :prefectures ");
			prefecturesFlg = true;
			andFlg = true;
		}
		if (status != 0) {
			if (andFlg) sql.append(" AND ");
			sql.append("g.status = :status ");
			statusFlg = true;
			andFlg = true;
		}
		if (local1 != null) {
			if (andFlg) sql.append(" AND ");
			sql.append("g.now >= :local1 ");
			local1Flg = true;
			andFlg = true;
		}
		if (local2 != null) {
			if (andFlg) sql.append(" AND ");
			sql.append("g.now >= :local2 ");
			local2Flg = true;
			andFlg = true;
		}
		Query query = manager.createQuery(sql.toString());
		if (nameFlg) query.setParameter("name", "%" + name + "%");
		if (phone_numberFlg) query.setParameter("phone_number", "%" + phone_number + "%");
		if (industryFlg) query.setParameter("industry_id",industry_id);
		if (prefecturesFlg) query.setParameter("prefectures", prefectures );
		if (statusFlg) query.setParameter("status", status );
		if (local1Flg) query.setParameter("local1", local1);
		if (local2Flg) query.setParameter("local2", local2);
		return query.getResultList();
	}
}
