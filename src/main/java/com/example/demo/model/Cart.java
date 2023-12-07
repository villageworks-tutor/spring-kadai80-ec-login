package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.Item;

@Component
@SessionScope
public class Cart {
	
	/**
	 * フィールド：商品リスト
	 */
	private List<Item> items = new ArrayList<>();
	
	/**
	 * カートの商品リストを取得する
	 * @return 商品リスト
	 */
	public List<Item> getItems() {
		return this.items;
	}

	/**
	 * カートに商品を追加する。
	 * @param target
	 */
	public void add(Item target) {
		// 追加する商品と同じカート内の商品を格納するインスタンスの初期化
		Item exists = null;
		// 商品リストおすべての商品を走査
		for (Item item : this.items) {
			if (item.getId() == target.getId()) {
				// 追加する商品の商品番号と一致した商品が見つかった場合
				exists = item;
				break;
			}
		}
		
		if (exists == null) {
			// カート内に追加する商品が見つかった場合：賞品リストに追加して強制的に終了
			this.items.add(target);
			return;
		}
		
		// カート内商品の数量を変更
		Integer newQuantity = exists.getQuantity() + target.getQuantity();
		exists.setQuantity(newQuantity);
		
	}

	/**
	 * カートに入っている商品の金額の総合計を取得する
	 * @return カートに入っている商品の金額の総合計
	 */
	public Integer getTotalPrice() {
		Integer total = 0;
		for (Item item : this.items) {
			total += item.getPrice() * item.getQuantity();
		}
		return total;
	}

	/**
	 * カートに入っている商品を削除する
	 * @param itemId 削除対象となる商品の商品番号
	 */
	public void delete(Integer itemId) {
		for (Item item : this.getItems()) {
			if (item.getId() == itemId) {
				// 削除対象の商品が見つかった場合：その商品を削除
				this.getItems().remove(item);
				break;
			}
		}
	}

	/**
	 * カートに入っている商品をすべて削除する
	 */
	public void clear() {
		this.items.clear();
	}
	
}
