package com.micro.grievance.repository;

import com.micro.grievance.model.Department;

import java.util.List;

public interface DepartmentRepository {
    Department findById(int id);
    List<Department> findAll();
    Department save(Department department);
    Department update(Department department);
    void delete(int id);
}
