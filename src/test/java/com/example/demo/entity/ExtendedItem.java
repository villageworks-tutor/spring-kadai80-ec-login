package com.example.demo.entity;

/**
 * Itemエンティティテスト用継承クラス
 */
public class ExtendedItem extends Item {

	/**
	 * デフォルトコンストラクタ
	 */
	public ExtendedItem() {}

	/**
	 * コンストラクタ
	 * @param categoryId
	 * @param name
	 * @param price
	 */
	public ExtendedItem(Integer categoryId, String name, Integer price) {
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
		this.quantity = 1;
	}
	
	/**
	 * コンストラクタ
	 * @param id
	 * @param categoryId
	 * @param name
	 * @param price
	 */
	public ExtendedItem(Integer id, Integer categoryId, String name, Integer price) {
		this(categoryId, name, price);
		this.id = id;
	}
	
	/**
	 * コンストラクタ
	 * @param id
	 * @param categoryId
	 * @param name
	 * @param price
	 * @param quantity
	 */
	public ExtendedItem(Integer id, Integer categoryId, String name, Integer price, Integer quantity) {
		this(categoryId, name, price);
		this.id = id;
		this.quantity = quantity;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Integer getQuantity() {
		return this.quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExtendedItem [");
		builder.append("id=" + id + ", ");
		builder.append("categoryId=" + categoryId + ", ");
		builder.append("name=" + name + ", ");
		builder.append("price=" + price + ", ");
		builder.append("quantity=" + quantity + "]");
		return builder.toString();
	}
	
}
