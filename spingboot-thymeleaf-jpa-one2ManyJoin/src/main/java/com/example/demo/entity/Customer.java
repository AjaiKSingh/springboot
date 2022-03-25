package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name="Customer")

public class Customer {

	  	@Id
	    @GeneratedValue
	    private int id;
	  	
	    private String name;
	    private String email;
	    private String gender;
	    
	    @OneToMany(targetEntity = Product.class,cascade = CascadeType.ALL)
	    @JoinColumn(name ="customer_id",referencedColumnName = "id")
	    private List<Product> products;

		public Customer() {
		}   
		
		public Customer(int id, String name, String email, String gender, List<Product> products) {
			this.id = id;
			this.name = name;
			this.email = email;
			this.gender = gender;
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public List<Product> getProducts() {
			return products;
		}

		public void setProducts(List<Product> products) {
			this.products = products;
		}

		@Override
		public String toString() {
			return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", gender=" + gender + ", products="
					+ products + "]";
		}
	    
	    
}
