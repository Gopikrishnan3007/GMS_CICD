package com.micro.grievance.serviceimpl;

import com.micro.grievance.model.Department;
import com.micro.grievance.repository.DepartmentRepository;
import com.micro.grievance.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void deleteDepartmentById(int id) {
        departmentRepository.delete(id);
    }

    @Override
    public Department updateDepartment(int id, Department department) {
        if (departmentRepository.findById(id) != null) {
            department.setDepartmentId(id);
            return departmentRepository.update(department);
        }
        return null;
    }
}
