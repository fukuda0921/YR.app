package com.example.app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.app.service.UserDetailsServiceImpl;

/**
 * SpringSecurityを利用するための設定クラス
 * ログイン処理でのパラメータ、画面遷移や認証処理でのデータアクセス先を設定する
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//UserDetailsServiceを利用出来るように＠Autowiredしておく
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

//認証用パスワードはハッシュ化して扱うためPasswordをハッシュ化する際に必要なBCryptPasswordEncoder()を返すメソッドを作成しておく。
    @Bean
    public PasswordEncoder passwordEncoder() {

    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	//これはハッシュ化済みの値をDBに登録する確認用に出力させるコード//
	String password = "1234";
        String digest = bCryptPasswordEncoder.encode(password);
        System.out.println("ハッシュ値 = " + digest);
	///////////////////////////////////////////////////////////////

        return new BCryptPasswordEncoder();
    }


    /**
     * 認可設定を無視するリクエストを設定
     * 静的リソース(image,javascript,css)を認可処理の対象から除外する
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        		.antMatchers("/resources/**");
    }

    /**
     * 認証・認可の情報を設定する
     * SpringSecurityのconfigureメソッドをオーバーライドしています。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//            	.antMatchers("/login").permitAll()
//                .anyRequest().authenticated();
//        http
//        	.formLogin()
//        		.loginPage("/login").permitAll()
//                .defaultSuccessUrl("/sample", true);
    	 http.formLogin()
         //ログイン画面は常にアクセス可能とする
         .loginPage("/login").permitAll()
         //ログインに成功したら検索画面に遷移する
         .defaultSuccessUrl("/sample")
         .and()
         //ログイン画面のcssファイルとしても共通のdemo.cssを利用するため、
         //src/main/resources/static/cssフォルダ下は常にアクセス可能とする
         .authorizeRequests().antMatchers("/css/**").permitAll()
         .and()    //かつ
         //それ以外の画面は全て認証を有効にする
         .authorizeRequests().anyRequest().authenticated()
         .and()    //かつ
         //ログアウト時はログイン画面に遷移する
         .logout().logoutSuccessUrl("/login").permitAll();
    }

    /**
     * 認証時に利用するデータソースを定義する設定メソッド
     * ここではDBから取得したユーザ情報をuserDetailsServiceへセットすることで認証時の比較情報としている
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
    //UserDetailsServiceを設定してDaoAuthenticationProviderを有効化する
     auth.userDetailsService(userDetailsService).
     //上記作成のエンコードを設定しハッシュ化する
     passwordEncoder(passwordEncoder());
    }
}
