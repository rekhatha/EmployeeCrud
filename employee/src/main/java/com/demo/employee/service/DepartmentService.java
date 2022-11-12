package com.demo.employee.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.employee.model.Departments;
import com.demo.employee.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository deptRepository;

	public Departments updateDepartment(Departments departmentDetails,Departments department) {
		department.setDepartmentName(departmentDetails.getDepartmentName());
		department.setManagerId(departmentDetails.getManagerId());
		return deptRepository.save(department);
	}

	public Map<String, Boolean> deleteDepartment(Departments department) {
		deptRepository.delete(department);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}

}
