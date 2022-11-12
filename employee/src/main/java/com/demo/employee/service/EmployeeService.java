package com.demo.employee.service;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.employee.model.Employee;
import com.demo.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee updateEmployee(@Valid Employee employeeDetails, Employee employee) {
		employee.setEmail(employeeDetails.getEmail());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setDepartmentId(employeeDetails.getDepartmentId());
		employee.setHireDate(employeeDetails.getHireDate());
		employee.setManagerId(employeeDetails.getManagerId());
		employee.setPhoneNumber(employeeDetails.getPhoneNumber());
		employee.setSalary(employeeDetails.getSalary());
		return employeeRepository.save(employee);
	}

	public Map<String, Boolean> deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}

	
	
}
