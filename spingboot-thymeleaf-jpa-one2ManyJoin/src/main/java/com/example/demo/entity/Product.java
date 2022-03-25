package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Product")
public class Product {

    @Id
    private int pid;
    
	private String productName;
    private int qty;
    private int price;

    public Product(int pid, String productName, int qty, int price) {
		this.pid = pid;
		this.productName = productName;
		this.qty = qty;
		this.price = price;
	}
    
    public Product()
    {
    	
    }

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", productName=" + productName + ", qty=" + qty + ", price=" + price + "]";
	}
    
    
}
