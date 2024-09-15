package com.micro.grievance.service;

import com.micro.grievance.model.Grievance;

import java.util.List;
import java.util.Map;

public interface GrievanceService {
    Grievance saveGrievance(Grievance grievance);
    Grievance getGrievanceById(int id);
    List<Grievance> getAllGrievances();
    void deleteGrievanceById(int id);
    Grievance updateGrievance(int id, Grievance grievance);
    List<Grievance> getGrievancesByUserId(int userId);
    List<Grievance> getGrievancesByDepartmentId(int departmentId);
    List<Grievance> getGrievancesByEmployeeId(int employeeId);
    Grievance updateGrievanceStatus(int grievanceId, String status );
    Grievance assignEmployeeToGrievance(int grievanceId, int employeeId);
    Grievance updateGrievanceConfirmation(int grievanceId, boolean closeConfirmation);
    int countAllGrievances();
    int countByStatus(String status);
    Map<String, Integer> countByDepartment();
}
