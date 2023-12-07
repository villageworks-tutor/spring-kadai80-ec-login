package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

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
		// リクエストパラメータの入力チェック
		List<String> errors = new ArrayList<String>();
		// 名前の入力チェック
		if (name.isEmpty()) {
			// 空文字列の場合：必須入力エラー
			errors.add("名前は必須です");
		}
		// 住所の入力チェック
		if (address.isEmpty()) {
			// 空文字列の場合：必須入力エラー
			errors.add("住所は必須です");
		}
		// 電話番号の入力チェック
		if (tel.isEmpty()) {
			// 空文字列の場合：必須入力エラー
			errors.add("電話番号は必須です");
		}
		// 電子メールの入力チェック
		if (email.isEmpty()) {
			// 空文字列の場合：必須入力エラー
			errors.add("e-mailは必須です");
		} else if (customerRepository.findByEmail(email) != null) {
			// 登録済のメールアドレスの場合：重複エラー
			errors.add("登録済のメールアドレスです");
		}
		// パスワードの入力チェック
		if  (password.isEmpty()) {
			// 空文字列の場合：必須入力エラー
			errors.add("パスワードは必須です");
		}
		
		// エラーがある場合
		if (errors.size() > 0) {
			// エラーメッセージをスコープに登録
			model.addAttribute("errors", errors);
			// 入力値をスコープに登録
			model.addAttribute("name", name);
			model.addAttribute("address", address);
			model.addAttribute("tel", tel);
			model.addAttribute("email", email);
			// 自画面遷移
			return "accountForm";
		}
		
		// リクエストパラメータをもとに顧客インスタンスを生成
		Customer customer = new Customer(name, address, tel, email, password);
		// 顧客インスタンスの永続化
		customerRepository.save(customer);
		// 画面遷移
		return "redirect:/login";
	}
	
}
