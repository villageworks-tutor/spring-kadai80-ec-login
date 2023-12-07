package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "customer_id")
	private Integer customerId;
	@Column(name = "ordered_on")
	private Date orderedOn;
	@Column(name = "total_price")
	private Integer totalPrice;
	
	/**
	 * デフォルトコンストラクタ
	 */
	public Order() {}

	/**
	 * コンストラクタ
	 * @param customerId
	 * @param orderedOn
	 * @param totalPrice
	 */
	public Order(Integer customerId, Date orderedOn, Integer totalPrice) {
		this.customerId = customerId;
		this.orderedOn = orderedOn;
		this.totalPrice = totalPrice;
	}

	/**
	 * コンストラクタ
	 * @param id
	 * @param customerId
	 * @param orderedOn
	 * @param totalPrice
	 */
	public Order(Integer id, Integer customerId, Date orderedOn, Integer totalPrice) {
		this(customerId, orderedOn, totalPrice);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Date getOrderedOn() {
		return orderedOn;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}
	
}
