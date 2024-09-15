package com.micro.grievance.repositoryimpl;

import com.micro.grievance.model.Grievance;
import com.micro.grievance.repository.GrievanceRepository;

import jakarta.mail.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class GrievanceRepositoryImpl implements GrievanceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Grievance findById(int id) {
        return entityManager.find(Grievance.class, id);
    }

    @Override
    public List<Grievance> findAll() {
        return entityManager.createQuery("SELECT g FROM Grievance g", Grievance.class).getResultList();
    }

    @Override
    public Grievance save(Grievance grievance) {
        entityManager.merge(grievance);
        return grievance;
    }

    @Override
    public Grievance update(Grievance grievance) {
        return entityManager.merge(grievance);
    }

    @Override
    public void delete(int id) {
        Grievance grievance = findById(id);
        if (grievance != null) {
            entityManager.remove(grievance);
        }
    }
    
    @Override
    public List<Grievance> findGrievancesByUserId(int userId) {
        // Define the JPQL query to fetch grievances based on userId
        String jpql = "SELECT g FROM Grievance g WHERE g.user.id = :userId";
        
        // Create a TypedQuery
        TypedQuery<Grievance> query = entityManager.createQuery(jpql, Grievance.class);
        
        // Set the parameter for userId
        query.setParameter("userId", userId);
        
        // Execute the query and return the result
        return query.getResultList();
    }
    
    @Override
    public List<Grievance> findGrievancesByDepartmentId(int departmentId) {
        // Define the JPQL query to fetch grievances based on userId
        String jpql = "SELECT g FROM Grievance g WHERE g.department.id = :departmentId";
        
        // Create a TypedQuery
        TypedQuery<Grievance> query = entityManager.createQuery(jpql, Grievance.class);
        
        // Set the parameter for userId
        query.setParameter("departmentId", departmentId);
        
        // Execute the query and return the result
        return query.getResultList();
    }

	@Override
	public List<Grievance> findGrievancesByEmployeeId(int employeeId) {
		// Define the JPQL query to fetch grievances based on userId
        String jpql = "SELECT g FROM Grievance g WHERE g.assignedEmployee.id = :employeeId";
        
        // Create a TypedQuery
        TypedQuery<Grievance> query = entityManager.createQuery(jpql, Grievance.class);
        
        // Set the parameter for userId
        query.setParameter("employeeId", employeeId);
        
        // Execute the query and return the result
        return query.getResultList();
	}
	
	 @Override
	    public int countAllGrievances() {
	        // Define the JPQL query to count all grievances
	        String jpql = "SELECT COUNT(g) FROM Grievance g";
	        
	        // Create a Query object
	        Query query = entityManager.createQuery(jpql);
	        
	        // Execute the query and return the result as int
	        return ((Long) query.getSingleResult()).intValue();
	    }

	    @Override
	    public int countByStatus(String status) {
	        // Define the JPQL query to count grievances by status
	        String jpql = "SELECT COUNT(g) FROM Grievance g WHERE g.status = :status";
	        
	        // Create a Query object
	        Query query = entityManager.createQuery(jpql);
	        
	        // Set the parameter for status
	        query.setParameter("status", status);
	        
	        // Execute the query and return the result as int
	        return ((Long) query.getSingleResult()).intValue();
	    }

	    @Override
	    public Map<String, Integer> countByDepartment() {
	        // Define the JPQL query to count grievances by department
	        String jpql = "SELECT d.departmentName, COUNT(g) FROM Grievance g JOIN g.department d GROUP BY d.departmentName";
	        
	        // Create a Query object
	        Query query = entityManager.createQuery(jpql);
	        
	        // Execute the query and process the results
	        List<Object[]> results = query.getResultList();
	        
	        Map<String, Integer> departmentCounts = new HashMap<>();
	        for (Object[] result : results) {
	            departmentCounts.put((String) result[0], ((Long) result[1]).intValue());
	        }
	        return departmentCounts;
	    }
}
