package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Address;
import com.example.demo.entity.Employee;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.EmployeeRepository;
@Controller
public class AddressController {

	@Autowired
	AddressRepository addressRepo;
	
/* ********************************* Get All ******************************************************/
	
	/**
	 * @return
	 */
	@GetMapping("/addresses")
	public ResponseEntity<List<Address>> getAddresses()
	{
		List<Address> addresses = new ArrayList<>();
		addressRepo.findAll()
			.forEach(addresses::add);
		
		System.out.println(addresses);
		
		if(null!=addresses && addresses.size()>0)
			return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
		else
			return new ResponseEntity<List<Address>>(HttpStatus.NO_CONTENT);
	}	
	


/* ********************************* Save a record ******************************************************/
	/**
	 * @param employee
	 * @return
	 */
	@PostMapping("/address")
	@ResponseBody
	public ResponseEntity<Address> createAddress(@RequestBody Address address)
	{
		System.out.println(address);
		
		Address retAddress = addressRepo.save(address);

		return new ResponseEntity<>(retAddress,HttpStatus.CREATED);

		/*
		 Sample input: POST:raw:json
		 http://localhost:8087/address
			{
			"addressId":10002,
			"empNo":8001,
			"addressType":"HOME",
			"address":"US_HO2"
			} 
			 
		 */
	}    
/* ********************************* get a record ******************************************************/    
    
	@GetMapping("/addresses/{id}")
	@ResponseBody
	public ResponseEntity<Address> getAddress(@PathVariable int id)
	{
		System.out.println(id);
		Optional<Address> address = addressRepo.findById(id); 
		System.out.println(address);
		if(address.isPresent())
			return new ResponseEntity<Address>(address.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
		
	}

/* ********************************* Delete a record ******************************************************/
	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/addresses/{id}")
	public ResponseEntity<Address> deleteAddress(@PathVariable int id)
	{
		System.out.println(id);
		
		Optional<Address> address = addressRepo.findById(id); 
		boolean isRecordAvailable=false;
		if(address.isPresent())
			isRecordAvailable=true;

		if(!isRecordAvailable)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		addressRepo.deleteById(id); 

		return new ResponseEntity<>(address.get(),HttpStatus.OK);
		
	}
    /* ********************************* Update a record ******************************************************/
    
	@PutMapping("/address/{id}/{add}")
	public ResponseEntity<Address> updateAddress(@PathVariable int id, @PathVariable String add)
	{
		System.out.println(id);
		Optional<Address> address = addressRepo.findById(id); 
		boolean isRecordAvailable=false;
		if(address.isPresent())
			isRecordAvailable=true;
		
		if(isRecordAvailable)
		{
			Address addressToUpdate=address.get();
			addressToUpdate.setAddress(add);
			
			Address retAddress = addressRepo.save(addressToUpdate);
			
			return new ResponseEntity<>(retAddress,HttpStatus.CREATED);
		}
			
		return new ResponseEntity<Address>(HttpStatus.NOT_MODIFIED);
	}	
	
}
