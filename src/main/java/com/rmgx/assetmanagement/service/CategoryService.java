package com.rmgx.assetmanagement.service;

import com.rmgx.assetmanagement.entity.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category addCategory(Category category);
    Category updateCategory(Long id, Category updatedCategory);
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);
    void deleteCategory(Long id) throws Exception;
	Category partialUpdateCategory(Long id, Category partialCategory);
	
}
