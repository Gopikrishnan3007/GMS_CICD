package com.micro.grievance.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "departmentId", nullable = false)
    private Department department;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, unique = true, length = 255)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false)
    private Integer taskCount = 0;
    
    @Column(nullable = false, length = 50)
    private String role = "Employee";

    @Column(nullable = false)
    private LocalDateTime createdAt;

	public Employee() {
		super();
	}

	public Employee(Integer employeeId, Department department, String name, String username, String password,
			String email, Integer taskCount, String role, LocalDateTime createdAt) {
		super();
		this.employeeId = employeeId;
		this.department = department;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.taskCount = taskCount;
		this.role = role;
		this.createdAt = createdAt;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", department=" + department + ", name=" + name + ", username="
				+ username + ", password=" + password + ", email=" + email + ", taskCount=" + taskCount + ", role="
				+ role + ", createdAt=" + createdAt + "]";
	}
    
	
    
}
