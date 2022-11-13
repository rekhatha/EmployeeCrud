package com.demo.employee.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private Long employeeId;
	
	@Size(min=2)
	@NotNull(message = "First Name is mandatory")
	@Column(name = "first_name")
	private String firstName;
	
	@Size(min=2)
	@NotNull(message = "Last Name is mandatory")
	@Column(name = "last_name")
	private String lastName;
	
	@Email
	@Column(name = "email")
	private String email;
	
	@Pattern(regexp="^(?=(?:\\D*\\d){8}$)\\d+(?:(?:-\\d+){0,2}|(?:\\s+\\d+){0,1})$",message="Phone number contains only digits and dashs")  
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@FutureOrPresent
	@Column(name = "hire_date")
	@Temporal(TemporalType.DATE)
	private Date hireDate;

	@Min(value = 1, message = "The value must be greater than zero")
	@Column(name = "salary")
	@Digits(integer=8, fraction=2)
	private BigInteger salary;
	
	@Column(name = "manager_id")
	private Long managerId;
	
	@Column(name = "department_id")
	private Long departmentId;

	public Employee() {
	}
	
	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public BigInteger getSalary() {
		return salary;
	}

	public void setSalary(BigInteger salary) {
		this.salary = salary;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", salary=" + salary
				+ ", managerId=" + managerId + ", departmentId=" + departmentId + "]";
	}
	
	
	
	
	
	
}
