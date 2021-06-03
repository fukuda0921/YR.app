package com.example.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity//エンティティクラスのアノテーション
@Table(name="customers")
public class Client {

	@Id//idであることをアノテーションで明示する
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")//DB上のカラム名を指定する
	private Integer id;

	@Column(name="name")
	private String name;

	@Column(name="postal_code")
	private String postal_code;

	@Column(name="prefectures_id")
	private Integer prefectures;

	@Column(name="street_address")
	private String street_address;

	@Column(name="phone_number")
	private String phone_number;

	@Column(name="phone_number_sub")
	private String phone_number_sub;

	@Column(name="industry_id")
	private Integer industry;

}
