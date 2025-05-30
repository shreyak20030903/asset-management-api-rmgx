package com.rmgx.assetmanagement.service;

import com.rmgx.assetmanagement.entity.Category;
import com.rmgx.assetmanagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category updateCategory(Long id, Category updatedCategory) {
        Category existing = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));

        // Update only required fields
        existing.setCategoryName(updatedCategory.getCategoryName());

        return categoryRepository.save(existing);
    }

    
    @Override
    public Category partialUpdateCategory(Long id, Category partialCategory) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        // Only update fields that are non-null
        if (partialCategory.getCategoryName() != null) {
            existing.setCategoryName(partialCategory.getCategoryName());
        }

        if (partialCategory.getDescription() != null) {
            existing.setDescription(partialCategory.getDescription());
        }

        return categoryRepository.save(existing);
    }




    @Override
    public void deleteCategory(Long id) throws Exception {
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isPresent()) {
            categoryRepository.delete(categoryOpt.get());
        } else {
            throw new Exception("Category not found with ID: " + id);
        }
    }

    
	
}
