package com.example.springrestplaces.service;

import com.example.springrestplaces.entity.Category;
import com.example.springrestplaces.exceptions.ResourceNotFoundException;
import com.example.springrestplaces.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id, id));
    }

    @Transactional
    public void saveCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Category with that name already exists");
        }

        // Continue with saving the new category if it doesn't exist
        categoryRepository.save(category);
    }
}
