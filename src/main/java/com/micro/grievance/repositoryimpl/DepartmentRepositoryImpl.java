package com.micro.grievance.repositoryimpl;

import com.micro.grievance.model.Department;
import com.micro.grievance.repository.DepartmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Department findById(int id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public List<Department> findAll() {
        return entityManager.createQuery("SELECT d FROM Department d", Department.class).getResultList();
    }

    @Override
    public Department save(Department department) {
        entityManager.merge(department);
        return department;
    }

    @Override
    public Department update(Department department) {
        return entityManager.merge(department);
    }

    @Override
    public void delete(int id) {
        Department department = findById(id);
        if (department != null) {
            entityManager.remove(department);
        }
    }
}
