
package com.revshop.RevShopP1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.RevShopP1.model.Category;
import com.revshop.RevShopP1.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Get category by its ID, returning an exception if not found
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Category not found with id: " + id));
    }

    // Save a new category to the database
    public void save(Category category) {
        categoryRepository.save(category);
    }

    // You can add more methods as needed, such as delete
}
