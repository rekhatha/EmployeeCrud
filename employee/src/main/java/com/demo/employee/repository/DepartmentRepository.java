package com.demo.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.employee.model.Departments;

public interface DepartmentRepository extends JpaRepository<Departments, Long>{

}
