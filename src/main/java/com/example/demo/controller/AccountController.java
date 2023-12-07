package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.model.Account;
import com.example.demo.repository.CustomerRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {
	
	@Autowired
	HttpSession session;
	@Autowired
	CustomerRepository customerRepository; 
	@Autowired
	Account account;
	
	// ログイン画面表示およびログアウト処理
	@GetMapping({"/", "/login", "/logout"})
	public String index() {
		// セッションスコープの破棄
		session.invalidate();
		// 画面遷移
		return "login";
	}
	
	// ログイン処理
	@PostMapping("/login")
	public String login(
			@RequestParam("name") String name,
			Model model) {
		// セッションスコープに登録されているアカウント情報にリクエストパラメータを登録
		account.setName(name);
		// 画面遷移
		return "redirect:/items";
	}
	
	// 会員登録画面表示
	@GetMapping("/account")
	public String create() {
		// 画面遷移
		return "accountForm";
	}
	
	// 会員登録処理
	@PostMapping("/account")
	public String  store(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "tel", defaultValue = "") String tel,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {
		// リクエストパラメータをもとに顧客インスタンスを生成
		Customer customer = new Customer(name, address, tel, email, password);
		// 顧客インスタンスの永続化
		customerRepository.save(customer);
		// 画面遷移
		return "redirect:/login";
	}
	
}
