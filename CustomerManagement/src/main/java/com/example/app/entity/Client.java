package com.example.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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

	@Column(name="status_id")
	private Integer status;

	@Column(name="user_id")
	private Integer user_id;

	@Column(name="call_day")
	private Date now;

	@Column(name="next_call_day")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date next_call_day;

	@Column(name="remarks")
	private String remarks;

}
