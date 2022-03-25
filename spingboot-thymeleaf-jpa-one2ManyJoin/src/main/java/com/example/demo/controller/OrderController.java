package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Product;

@RestController
public class OrderController {

	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@GetMapping("/findAllOrders")
	public List<Customer> findAllOrders()
	{
		
		List<Customer> customers = new ArrayList<>();
		customerRepo.findAll()
			.forEach(customers::add);
		
		System.out.println(customers);
		return customers;
	}
	
    @PostMapping("/placeOrder")
    public Customer placeOrder(@RequestBody OrderRequest request){
       return customerRepo.save(request.getCustomer());
    }
    
    @GetMapping("/getCustomerProducts")
    public List<OrderResponse> getJoinInformation(){
        return customerRepo.getProductNameForCustomers();
    }
}
