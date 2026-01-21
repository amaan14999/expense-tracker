package com.amaan.expense_tracker.service;

import com.amaan.expense_tracker.domain.Category;
import com.amaan.expense_tracker.domain.CategoryType;
import com.amaan.expense_tracker.domain.User;
import com.amaan.expense_tracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(User user, String name, CategoryType type) {
        if(categoryRepository.existsByUser_IdAndNameIgnoreCase(user.getId(), name)) {
            throw new IllegalArgumentException("Category with the same name already exists");
        }

        Category category = Category.builder()
                .user(user)
                .name(name.trim())
                .type(type)
                .build();

        try {
            return categoryRepository.save(category);
        } catch (DataIntegrityViolationException ex) {
            // race condition safety: if two requests create same category simultaneously
            throw new IllegalArgumentException("Category with same name already exists");
        }
    }

    public List<Category> listCategories(User user) {
        return categoryRepository.findByUser_IdOrderByNameAsc(user.getId());
    }
}
