package com.micro.grievance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.grievance.model.Department;
import com.micro.grievance.model.Employee;
import com.micro.grievance.model.Login;
import com.micro.grievance.model.User;
import com.micro.grievance.service.DepartmentService;
import com.micro.grievance.service.EmployeeService;
import com.micro.grievance.service.UserService;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
    private DepartmentService departmentService;
 
    @Autowired
    private UserService userService;
    
    @Autowired
    private EmployeeService employeeService;
 
    @PostMapping
    public Object login(@RequestBody Login login) {
    	
        String username = login.getUsername();
        String password = login.getPassword();

        // Authenticate department
        List<Department> departments = departmentService.getAllDepartments();
        for (Department department : departments) {
            if (department.getUsername().equals(username) && department.getPassword().equals(password)) {
                return department; 
            }
        }
 
        // Authenticate User
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; 
            }
        }
        
        List<Employee> employees = employeeService.getAllEmployees();
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                return employee; 
            }
        }
 
        return "Login failed: Invalid email or password";
    }
	
	

}
