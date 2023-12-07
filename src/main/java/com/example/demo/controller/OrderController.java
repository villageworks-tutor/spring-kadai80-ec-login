package com.example.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.model.Cart;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

@Controller
public class OrderController {
	
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@Autowired
	Cart cart;
	
	// 顧客情報入力画面表示
	@GetMapping("/order")
	public String index() {
		// 画面遷移
		return "customerForm";
	}
	
	// 注文内容確認画面表示
	@PostMapping("/order/confirm")
	public String confirm(
			@RequestParam("name") String name,
			@RequestParam("address") String  address,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			Model model) {
		// リクエストパラメータをもとに顧客インスタンスを生成
		Customer customer = new Customer(name, address, tel, email);
		// 生成した顧客インスタンスをスコープに登録
		model.addAttribute("customer", customer);
		// 画面遷移
		return "orderConfirm";
	}
	
	// 注文確定処理
	@PostMapping("/order")
	public String doOrder(
			@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			Model model) {
		// リクエストパラメータをもとに顧客クラスをインスタンス化
		Customer customer = new Customer(name, address, tel, email);
		// 顧客インスタンスを永続化
		customerRepository.save(customer);

		// 注文クラスをインスタンス化
		Date today = new Date(System.currentTimeMillis());
		Order order = new Order(customer.getId(), today, cart.getTotalPrice());
		// 注文インスタンスを永続化
		orderRepository.save(order);
		
		// セッションスコープに登録されているカート情報をもとに注文詳細リストを生成
		List<OrderDetail> details = new ArrayList<>();
		for (Item item : cart.getItems()) {
			details.add(new OrderDetail(order.getId(), item.getId(), item.getQuantity()));
		}
		// 注文詳細リストを永続化
		orderDetailRepository.saveAll(details);
		
		// カートの商品をクリア
		cart.clear();
		
		// 注文番号をスコープに登録
		model.addAttribute("orderNo", order.getId());
		
		// 画面遷移
		return "ordered";
	}
}
