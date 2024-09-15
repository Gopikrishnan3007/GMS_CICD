package com.micro.grievance.service;

import com.micro.grievance.model.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);
    Department getDepartmentById(int id);
    List<Department> getAllDepartments();
    void deleteDepartmentById(int id);
    Department updateDepartment(int id, Department department);
}
