package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name="myemp")
public class Employee {
	@Id
	@Column(name="empno")
	int empNo;

	@Column(name="empname")
	String empName; //emp_name
	
	int sal;

	@OneToMany(mappedBy="employee" , cascade=CascadeType.ALL, orphanRemoval=true)
	//@JoinColumn(name="empno")
    List<Address> addresses;

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
		
		//extra
		for(Address address:addresses)
		{
			address.setEmployee(this);
		}
	}
	public Employee()
	{
		
	}

	public Employee(int empNo, String empName, int sal, List<Address> addresses) {
		this.empNo = empNo;
		this.empName = empName;
		this.sal = sal;
		this.addresses = addresses;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", empName=" + empName + ", sal=" + sal + addresses.toString() + "]"  ;
	}

}
