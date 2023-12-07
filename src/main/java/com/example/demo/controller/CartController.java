package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.model.Cart;
import com.example.demo.repository.ItemRepository;

@Controller
public class CartController {
	
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	Cart cart;
	
	// カート画面表示
	@GetMapping("/cart")
	public String index() {
		// 画面遷移
		return "cart";
	}
	
	// カートに商品を追加
	@PostMapping("/cart/add")
	public String addCart(
			@RequestParam("itemId") Integer itemId,
			@RequestParam(name = "quantity", defaultValue = "1") Integer quantity){
		// リクエストパラメータをもとに商品を取得
		Item item = itemRepository.findById(itemId).get();
		item.setQuantity(quantity);
		// セッションスコープに登録されているカートに取得した商品を投入
		cart.add(item);
		// 画面遷移：リダイレクト
		return "redirect:/cart";
	}
	
	// カートの商品を削除
	@PostMapping("/cart/delete")
	public String deleteCart(@RequestParam("itemId") Integer itemId) {
		// リクエストパラメータをもとにして商品リストを削除
		cart.delete(itemId);
		// 画面遷移：リダイレクト
		return "redirect:/cart";
	}
	
}
