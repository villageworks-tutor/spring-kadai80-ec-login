package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "order_id")
	private Integer orderId;
	@Column(name = "item_id")
	private Integer itemId;
	private Integer quantity;
	
	/**
	 * デフォルトコンストラクタ
	 */
	public OrderDetail() {}

	
	/**
	 * コンストラクタ
	 * @param orderId
	 * @param itemId
	 * @param quantity
	 */
	public OrderDetail(Integer orderId, Integer itemId, Integer quantity) {
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	/**
	 * コンストラクタ
	 * @param id
	 * @param orderId
	 * @param itemId
	 * @param quantity
	 */
	public OrderDetail(Integer id, Integer orderId, Integer itemId, Integer quantity) {
		this(orderId, itemId, quantity);
		this.id = id;
	}


	public Integer getId() {
		return id;
	}


	public Integer getOrderId() {
		return orderId;
	}


	public Integer getItemId() {
		return itemId;
	}


	public Integer getQuantity() {
		return quantity;
	}
	
}
