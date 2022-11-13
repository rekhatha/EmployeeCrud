package com.demo.employee.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employee.exception.ResourceNotFoundException;
import com.demo.employee.model.Departments;
import com.demo.employee.repository.DepartmentRepository;
import com.demo.employee.service.DepartmentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

	@Autowired
	private DepartmentRepository deptRepository;

	@Autowired
	private DepartmentService deptService;

	
	@GetMapping("/departments")
	public List<Departments> getAllDepartments() {
		return deptRepository.findAll();
	}

	@GetMapping("/departments/{id}")
	public ResponseEntity<Departments> getEmployeeById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Departments department = deptRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Department Found for this id :: " + id));
		return ResponseEntity.ok().body(department);
	}

	@PostMapping("/departments")
	public Departments createDepartment(@Valid @RequestBody Departments department) {
		return deptRepository.save(department);
	}

	@PutMapping("/departments/{id}")
	public ResponseEntity<Departments> updateDepartment(@PathVariable(value = "id") Long departmentId,
			@Valid @RequestBody Departments departmentDetails) throws ResourceNotFoundException {
		Departments department = deptRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("No Department Found for this id :: " + departmentId));

		return ResponseEntity.ok(deptService.updateDepartment(departmentDetails,department));
	}

	@DeleteMapping("/departments/{id}")
	public Map<String, Boolean> deleteDepartment(@PathVariable(value = "id") Long departmentId)
			throws ResourceNotFoundException {
		Departments department = deptRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("No Department Found for this id :: " + departmentId));
		
		return deptService.deleteDepartment(department);
	}
	
}
