package com.micro.grievance.service;

import com.micro.grievance.model.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);
    Category getCategoryById(int id);
    List<Category> getAllCategories();
    void deleteCategoryById(int id);
    Category updateCategory(int id, Category category);
    
    List<Category> getCategoriesByDepartmentId(Integer departmentId);
}
