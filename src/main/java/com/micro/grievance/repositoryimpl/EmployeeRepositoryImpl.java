package com.micro.grievance.repositoryimpl;

import com.micro.grievance.model.Employee;
import com.micro.grievance.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAll() {
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    @Override
    public Employee save(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public void delete(int id) {
        Employee employee = findById(id);
        if (employee != null) {
            entityManager.remove(employee);
        }
    }
    
    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) {
        String query = "SELECT e FROM Employee e WHERE e.department.id = :departmentId";
        TypedQuery<Employee> typedQuery = entityManager.createQuery(query, Employee.class);
        typedQuery.setParameter("departmentId", departmentId);
        return typedQuery.getResultList();
    }
}
