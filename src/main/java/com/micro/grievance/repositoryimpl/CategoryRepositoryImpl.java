package com.micro.grievance.repositoryimpl;

import com.micro.grievance.model.Category;
import com.micro.grievance.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category findById(int id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        return entityManager.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    @Override
    public Category save(Category category) {
        entityManager.persist(category);
        return category;
    }

    @Override
    public Category update(Category category) {
        return entityManager.merge(category);
    }

    @Override
    public void delete(int id) {
        Category category = findById(id);
        if (category != null) {
            entityManager.remove(category);
        }
    }
    
    @Override
    public List<Category> findCategoriesByDepartmentId(Integer departmentId) {
        String jpql = "SELECT c FROM Category c WHERE c.department.departmentId = :departmentId";
        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
        query.setParameter("departmentId", departmentId);
        return query.getResultList();
    }
}
