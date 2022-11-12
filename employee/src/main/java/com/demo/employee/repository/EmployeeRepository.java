package com.demo.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	public List<Employee> findByDepartmentId(Long departmentId);
	
	public List<Employee> findByManagerId(Long managerId);
	
}
