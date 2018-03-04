package com.bean;

import java.util.List;

public class Category {
	private int id;
	private String name;
	List<Product> products;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Category(String name) {
		super();
		this.name = name;
	}


	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Categoty [id=" + id + ",name=" + name + "]";
	}

}
