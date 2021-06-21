package com.example.app.from;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class client {
	private Integer id;
	private String name;
	private String postal_code;
	private Integer prefectures;
	private String street_address;
	private String phone_number;
	private String phone_number_sub;
	private Integer industry;
	private Integer status;

	private Integer user_id;
	private Date now;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date next_call_day;

	private String remarks;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date local1;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date local2;


}
