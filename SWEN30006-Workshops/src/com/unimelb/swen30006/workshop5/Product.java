package com.unimelb.swen30006.workshop5;

public class Product {
	private String productType;
	public Product(String productType) {
		this.productType = productType;
	}
	
	public String getProductType() {
		return new String(productType);
	}
}
