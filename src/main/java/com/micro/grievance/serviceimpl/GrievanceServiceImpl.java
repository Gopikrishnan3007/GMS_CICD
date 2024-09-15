package com.micro.grievance.serviceimpl;

import com.micro.grievance.model.Employee;
import com.micro.grievance.model.Grievance;
import com.micro.grievance.repository.EmployeeRepository;
import com.micro.grievance.repository.GrievanceRepository;
import com.micro.grievance.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GrievanceServiceImpl implements GrievanceService {

    @Autowired
    private GrievanceRepository grievanceRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Grievance saveGrievance(Grievance grievance) {
        return grievanceRepository.save(grievance);
    }

    @Override
    public Grievance getGrievanceById(int id) {
        return grievanceRepository.findById(id);
    }

    @Override
    public List<Grievance> getAllGrievances() {
        return grievanceRepository.findAll();
    }

    @Override
    public void deleteGrievanceById(int id) {
        grievanceRepository.delete(id);
    }

    @Override
    public Grievance updateGrievance(int id, Grievance grievance) {
        if (grievanceRepository.findById(id) != null) {
            grievance.setGrievanceId(id);
            return grievanceRepository.update(grievance);
        }
        return null;
    }
    
    @Override
    public List<Grievance> getGrievancesByUserId(int userId) {
        return grievanceRepository.findGrievancesByUserId(userId);
    }
    
    @Override
    public Grievance updateGrievanceStatus(int grievanceId, String status) {
        Grievance grievance = grievanceRepository.findById(grievanceId);
        grievance.setStatus(status);
        grievance.setUpdatedAt(LocalDateTime.now());
        return grievanceRepository.save(grievance);
    }
    
    @Override
    public List<Grievance> getGrievancesByDepartmentId(int departmentId) {
        return grievanceRepository.findGrievancesByDepartmentId(departmentId);
    }
    
//    @Override
//    public Grievance assignEmployeeToGrievance(int grievanceId, int employeeId) {
//        Optional<Grievance> optionalGrievance = Optional.ofNullable(grievanceRepository.findById(grievanceId));
//        Optional<Employee> optionalEmployee = Optional.ofNullable(employeeRepository.findById(employeeId));
//
//        if (optionalGrievance.isPresent() && optionalEmployee.isPresent()) {
//            Grievance grievance = optionalGrievance.get();
//            Employee employee = optionalEmployee.get();
//
//            grievance.setAssignedEmployee(employee);
//            return grievanceRepository.save(grievance);
//        } else {
//            // Return null or handle as appropriate for your use case
//            return null;
//        }
//    }
    public Grievance assignEmployeeToGrievance(int grievanceId, int employeeId) {
        Optional<Grievance> optionalGrievance = Optional.ofNullable(grievanceRepository.findById(grievanceId));
        Optional<Employee> optionalEmployee = Optional.ofNullable(employeeRepository.findById(employeeId));

        if (optionalGrievance.isPresent() && optionalEmployee.isPresent()) {
            Grievance grievance = optionalGrievance.get();
            Employee employee = optionalEmployee.get();

            // Update task count of the previously assigned employee (if any)
            if (grievance.getAssignedEmployee() != null) {
                Employee previousEmployee = grievance.getAssignedEmployee();
                previousEmployee.setTaskCount(previousEmployee.getTaskCount() - 1);
                employeeRepository.save(previousEmployee);
            }

            // Assign new employee and update task count
            grievance.setAssignedEmployee(employee);
            employee.setTaskCount(employee.getTaskCount() + 1);
            employeeRepository.save(employee);

            return grievanceRepository.save(grievance);
        }
        return null;
    }
    
    public Grievance updateGrievanceConfirmation(int grievanceId, boolean closeConfirmation) {
        Grievance grievance = grievanceRepository.findById(grievanceId);
        if (grievance != null) {
            grievance.setCloseConfirmation(closeConfirmation);
            grievance.setStatus(closeConfirmation ? "Closed" : "Re-Pending");
            return grievanceRepository.save(grievance);
        }
        return null;
    }
    

	@Override
	public List<Grievance> getGrievancesByEmployeeId(int employeeId) {
		return grievanceRepository.findGrievancesByEmployeeId(employeeId);
	}
    
    
    
// for close the grievance and - the emp task count
//    public void closeGrievance(int grievanceId) {
//        Optional<Grievance> optionalGrievance = grievanceRepository.findById(grievanceId);
//        if (optionalGrievance.isPresent()) {
//            Grievance grievance = optionalGrievance.get();
//            if (grievance.getAssignedEmployee() != null) {
//                Employee employee = grievance.getAssignedEmployee();
//                employee.setTaskCount(employee.getTaskCount() - 1);
//                employeeRepository.save(employee);
//            }
//            grievance.setStatus("Closed");
//            grievanceRepository.save(grievance);
//        }
//    }
	
	@Override
    public int countAllGrievances() {
        return grievanceRepository.countAllGrievances();
    }

    @Override
    public int countByStatus(String status) {
        return grievanceRepository.countByStatus(status);
    }

    @Override
    public Map<String, Integer> countByDepartment() {
        return grievanceRepository.countByDepartment();
    }
}
