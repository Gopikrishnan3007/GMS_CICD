package com.micro.grievance.repository;

import com.micro.grievance.model.Grievance;

import java.util.List;
import java.util.Map;

public interface GrievanceRepository {
    Grievance findById(int id);
    List<Grievance> findAll();
    Grievance save(Grievance grievance);
    Grievance update(Grievance grievance);
    void delete(int id);
    List<Grievance> findGrievancesByUserId(int userId);
    List<Grievance> findGrievancesByDepartmentId(int departmentId);
    List<Grievance> findGrievancesByEmployeeId(int employeeId);
    
    int countAllGrievances();
    int countByStatus(String status);
    Map<String, Integer> countByDepartment();
}
