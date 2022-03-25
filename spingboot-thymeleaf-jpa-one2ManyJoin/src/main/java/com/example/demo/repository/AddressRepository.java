package com.example.demo.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>{
	
}
