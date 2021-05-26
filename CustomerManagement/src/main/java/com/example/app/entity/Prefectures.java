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
@Table(name="prefectures")
public class Prefectures {
	@Id//idであることをアノテーションで明示する
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")//DB上のカラム名を指定する
	private Integer id;

	@Column(name="prefectures")
	private String prefectures;

}
