package com.micro.grievance.repository;

import com.micro.grievance.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    Employee findById(int id);
    List<Employee> findAll();
    Employee save(Employee employee);
    Employee update(Employee employee);
    void delete(int id);
    List<Employee> getEmployeesByDepartmentId(int departmentId);
}
