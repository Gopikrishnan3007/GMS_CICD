package com.micro.grievance.serviceimpl;

import com.micro.grievance.model.Category;
import com.micro.grievance.repository.CategoryRepository;
import com.micro.grievance.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategoryById(int id) {
        categoryRepository.delete(id);
    }

    @Override
    public Category updateCategory(int id, Category category) {
        if (categoryRepository.findById(id) != null) {
            category.setCategoryId(id);
            return categoryRepository.update(category);
        }
        return null;
    }
    
    @Override
    public List<Category> getCategoriesByDepartmentId(Integer departmentId) {
        return categoryRepository.findCategoriesByDepartmentId(departmentId);
    }
}
