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

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepo;
	
/* ********************************* Get All ******************************************************/
	
	@RequestMapping(value = "/queries/employeeAdministration", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
		List<Employee> empList = new ArrayList<>();
		empRepo.findAll()
			.forEach(empList::add);
			
		ModelAndView mv = new ModelAndView("/queries/employeeAdministration");
		mv.addObject("listEmployees", empList);

		return mv;
		
		//http://localhost:8087/queries/employeeAdministration
	}


	/**
	 * @return
	 */
	@GetMapping("/queries/employeeAdministration/employees")
	public ResponseEntity<List<Employee>> getEmployees()
	{
		List<Employee> employees = new ArrayList<>();
		empRepo.findAll()
			.forEach(employees::add);
		
		System.out.println(employees);
		if(null!=employees && employees.size()>0)
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		else
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
	}	
	

/* ********************************* new form ******************************************************/
	
    @GetMapping("/queries/employeeAdministration/showNewEmployeeForm")
    public ModelAndView showNewEmployeeForm() {
        // create model attribute to bind form data
        Employee employee = new Employee();
		ModelAndView mv = new ModelAndView("/queries/new_employee");
		mv.addObject("employee", employee);

		return mv;

    }


/* ********************************* Save a record ******************************************************/
    @PostMapping("/queries/employeeAdministration/saveEmployee")
    public ModelAndView saveEmployee(@ModelAttribute("employee") Employee employee) {
        // save employee to database
    	empRepo.save(employee);
        ModelAndView mv = new ModelAndView("redirect:/queries/employeeAdministration");
        return mv;
    }

	/**
	 * @param employee
	 * @return
	 */
	@PostMapping("/queries/employeeAdministration/employee")
	@ResponseBody
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
	{
		System.out.println(employee);
		
		Employee retEmployee = empRepo.save(employee);

		return new ResponseEntity<>(retEmployee,HttpStatus.CREATED);

		/*
		 Sample input: POST:raw:json
		 http://localhost:8081/queries/employeeAdministration/employee
		 {
		    "empNo":60001,
		    "empName":"Emp60001",
		    "sal":60001
		} 
		 
		 */
	}    
/* ********************************* get a record ******************************************************/    
    @GetMapping("/queries/employeeAdministration/showFormForUpdate/{empNo}")
    public ModelAndView showFormForUpdate(@PathVariable(value = "empNo") int empNo) {

        // get employee from the service
        Optional <Employee> empOptional = empRepo.findById(empNo);
        Employee employee = null;
        if (empOptional.isPresent()) {
            employee = empOptional.get();
        } else {
            throw new RuntimeException(" Employee not found for empNo :: " + empNo);
        }

        // set employee as a model attribute to pre-populate the form
        ModelAndView mv = new ModelAndView("/queries/update_employee");
        mv.addObject("employee", employee);
        return mv;
    }

    
	@GetMapping("/queries/employeeAdministration/employee/{empNo}")
	@ResponseBody
	public ResponseEntity<Employee> getEmployee(@PathVariable int empNo)
	{
		System.out.println(empNo);
		Optional<Employee> employee = empRepo.findById(empNo); 
		System.out.println(employee);
		if(employee.isPresent())
			return new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		
	}

/* ********************************* Delete a record ******************************************************/
    @GetMapping("/queries/employeeAdministration/deleteEmployee/{empNo}")
    public ModelAndView deleteEmployee(@PathVariable(value = "empNo") int empNo) {
    	System.out.println(empNo);
        // call delete employee method 
    	empRepo.deleteById(empNo);
        
        ModelAndView mv = new ModelAndView("redirect:/queries/employeeAdministration");
        return mv;
        
        /* http://localhost:8087/queries/employeeAdministration/deleteEmployee/10010 */
    }

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/queries/employeeAdministration/employee/{empNo}")
	public ResponseEntity<Employee> deleteEmployeeJ(@PathVariable int id)
	{
		System.out.println(id);
		
		Optional<Employee> employee = empRepo.findById(id); 
		boolean isRecordAvailable=false;
		if(employee.isPresent())
			isRecordAvailable=true;

		if(!isRecordAvailable)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		empRepo.deleteById(id); 
		//empJPA.delete(employee.get());

		return new ResponseEntity<>(employee.get(),HttpStatus.OK);
		
	}
	
    /* ********************************* Update a record ******************************************************/

	@PutMapping("/queries/employeeAdministration/employee/{id}/{name}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @PathVariable String name)
	{
		System.out.println(id);
		Optional<Employee> employee = empRepo.findById(id); 
		boolean isRecordAvailable=false;
		if(employee.isPresent())
			isRecordAvailable=true;
		
		if(isRecordAvailable)
		{
			Employee empToUpdate=employee.get();
			empToUpdate.setEmpName(name);
			
			return new ResponseEntity<>(empRepo.save(empToUpdate),HttpStatus.OK);
		}
			
		return new ResponseEntity<Employee>(HttpStatus.NOT_MODIFIED);
	}	
	
}
