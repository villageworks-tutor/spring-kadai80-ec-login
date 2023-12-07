package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	@Column(name = "category_id")
	protected Integer categoryId;
	protected String name;
	protected Integer price;
	@Transient
	protected Integer quantity;

	
	/**
	 * デフォルトコンストラクタ
	 */
	public Item() {}

	/**
	 * コンストラクタ
	 * @param categoryId
	 * @param name
	 * @param price
	 * @param quantity
	 */
	public Item(Integer categoryId, String name, Integer price, Integer quantity) {
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	/**
	 * コンストラクタ
	 * @param id
	 * @param categoryId
	 * @param name
	 * @param price
	 * @param quantity
	 */
	public Item(Integer id, Integer categoryId, String name, Integer price, Integer quantity) {
		this(categoryId, name, price, quantity);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Integer getQuantity() {
		return this.quantity;
	}
	
}
