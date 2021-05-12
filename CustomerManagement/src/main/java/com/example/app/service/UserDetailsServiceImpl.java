package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.app.entity.User;
import com.example.app.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
 //ユーザーテーブルの取得用repositoryのインスタンス生成
 @Autowired//オートワイヤリング設定(DIコンテナから型が一致するものを取り出しインジェクションする)
    private UserRepository userRepository;

 //与えられたユーザー名を用いてUserDetailsを取得し返却するメソッド
 @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 //データベースからアカウント情報を検索する
//     User user = userRepository.find(username);
      User probe = new User();
         probe.setUsername(username);

         // 検索条件からExampleインスタンスを生成
         Example<User> example = Example.of(probe);
         User user = userRepository.findOne(example).get();
     return user;
    }


    public void save(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		System.out.println(user);
		userRepository.save(user);
	}

}