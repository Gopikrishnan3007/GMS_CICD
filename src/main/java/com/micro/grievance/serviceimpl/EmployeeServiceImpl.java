package com.micro.grievance.serviceimpl;

import com.micro.grievance.model.Employee;
import com.micro.grievance.repository.EmployeeRepository;
import com.micro.grievance.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.delete(id);
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        if (employeeRepository.findById(id) != null) {
            employee.setEmployeeId(id);
            return employeeRepository.update(employee);
        }
        return null;
    }
    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) {
        return employeeRepository.getEmployeesByDepartmentId(departmentId);
    }
}
