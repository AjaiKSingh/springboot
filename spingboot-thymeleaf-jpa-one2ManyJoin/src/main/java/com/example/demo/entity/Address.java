package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="myemp_address")
public class Address {
	@Id
	@Column(name="address_id")
	int addressId;
	
//	@Column(name="empno")
//	int empNo;

	@Column(name="address_type")
	String addressType; 
	
	@Column(name="address")
	String address; 

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="empno", nullable=false)
	private Employee employee;

	@JsonIgnore
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

//	public int getEmpNo() {
//		return empNo;
//	}
//
//	public void setEmpNo(int empNo) {
//		this.empNo = empNo;
//	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Address() {
	}

//	public Address(int addressId, int empNo, String addressType, String address) {
//		this.addressId = addressId;
//		this.empNo = empNo;
//		this.addressType = addressType;
//		this.address = address;
//	}

	public Address(int addressId, String addressType, String address, Employee employee) {
	this.addressId = addressId;
	this.employee = employee;
	this.addressType = addressType;
	this.address = address;
}
//	@Override
//	public String toString() {
//		return "Address [addressId=" + addressId + ", empNo=" + empNo + ", addressType=" + addressType + ", address="
//				+ address + "]";
//	}



}
