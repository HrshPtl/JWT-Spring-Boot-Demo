package com.byteparity.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byteparity.employee.exception.ResourceNotFoundException;
import com.byteparity.employee.model.Employee;
import com.byteparity.employee.repository.EmployeeRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Employee", value = "Employee API's", description = "Controller for Employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}
	
	@PostMapping("/employee/save")
	public Employee save(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employee/{empId}")
	public Employee getUserById(@PathVariable(value = "empId") Long empId){
		return employeeRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "empId", empId));
	}
	
	@PutMapping("/employee/{empId}")
	public Employee updateEmployee(@PathVariable(value = "empId") Long empId, @Valid @RequestBody Employee employeeDetail){
		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "empId", empId));
		employee.setFirstName(employeeDetail.getFirstName());
		employee.setLastName(employeeDetail.getLastName());
		employee.setDob(employeeDetail.getDob());
		employee.setEmail(employeeDetail.getEmail());
		employee.setContactNumber(employeeDetail.getContactNumber());
		Employee updatedEntry= employeeRepository.save(employee);
		return updatedEntry;
	}
	
	@DeleteMapping("/employee/{empId}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "empId") Long empId){
		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "empId", empId));
		employeeRepository.delete(employee);
		return ResponseEntity.ok().build();
	}
}
