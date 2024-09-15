package com.micro.grievance.repository;

import com.micro.grievance.model.Category;

import java.util.List;

public interface CategoryRepository {
    Category findById(int id);
    List<Category> findAll();
    Category save(Category category);
    Category update(Category category);
    void delete(int id);
    List<Category> findCategoriesByDepartmentId(Integer departmentId);
}
