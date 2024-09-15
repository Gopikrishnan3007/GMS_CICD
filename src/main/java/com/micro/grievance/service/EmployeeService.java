package com.micro.grievance.service;

import com.micro.grievance.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();
    void deleteEmployeeById(int id);
    Employee updateEmployee(int id, Employee employee);
    List<Employee> getEmployeesByDepartmentId(int departmentId);
}
