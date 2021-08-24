package com.cb.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cb.employee.data.Employee;
import com.cb.employee.service.NameService;

@RestController
public class EmployeeController {
	@Autowired
	private NameService nameservice;

	@GetMapping("/api/v1/employees")
	private ResponseEntity<Employee> getEmployee() {
		return new ResponseEntity<Employee>(new Employee(1, nameservice.callNameService(), "Tenkasi"), HttpStatus.OK);
	}

}
