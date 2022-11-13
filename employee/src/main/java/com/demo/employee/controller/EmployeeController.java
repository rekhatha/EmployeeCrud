package com.demo.employee.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employee.exception.ResourceNotFoundException;
import com.demo.employee.model.Departments;
import com.demo.employee.model.Employee;
import com.demo.employee.repository.DepartmentRepository;
import com.demo.employee.repository.EmployeeRepository;
import com.demo.employee.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Validated
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentRepository deptRepository;


	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Employee createEmployee(@RequestBody @Valid Employee employee) throws MethodArgumentNotValidException , ResourceNotFoundException {
		Departments dept = deptRepository.findById(employee.getDepartmentId())
				.orElseThrow(() -> new ResourceNotFoundException("Department Doesnt exist for this id :: " + employee.getDepartmentId()));
		
		return employeeRepository.save(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws MethodArgumentNotValidException,ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		return ResponseEntity.ok(employeeService.updateEmployee(employeeDetails,employee));
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		
		return employeeService.deleteEmployee(employee);
	}
	
	@GetMapping("/employeesByDept/{departmentId}")
	public ResponseEntity<List<Employee>> getEmployeeByDepartmentId(@PathVariable(value = "departmentId") Long departmentId)
			throws ResourceNotFoundException {
		
		Departments dept = deptRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department Doesnt exist for this id :: " + departmentId));
		
		List<Employee> employee = employeeRepository.findByDepartmentId(departmentId);
		
		if (employee.isEmpty()) {
			  throw new ResourceNotFoundException("Employee not found for this Department :: " + dept.getDepartmentName());
		}
		return ResponseEntity.ok().body(employee);
	}
	
	@GetMapping("/employeesByManager/{managerId}")
	public ResponseEntity<List<Employee>> getEmployeeByManagerId(@PathVariable(value = "managerId") Long managerId)
			throws ResourceNotFoundException {
		List<Employee> employee = employeeRepository.findByManagerId(managerId);
		
		if (employee.isEmpty()) {
			  throw new ResourceNotFoundException("Employee not found for this Manager :: " + managerId);
		}
		return ResponseEntity.ok().body(employee);
	}
}
