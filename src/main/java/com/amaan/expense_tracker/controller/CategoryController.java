package com.amaan.expense_tracker.controller;

import com.amaan.expense_tracker.controller.dto.CategoryResponse;
import com.amaan.expense_tracker.controller.dto.CreateCategoryRequest;
import com.amaan.expense_tracker.domain.Category;
import com.amaan.expense_tracker.domain.User;
import com.amaan.expense_tracker.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponse create(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest
    ) {
        Category created = categoryService.createCategory(
                user,
                createCategoryRequest.name(),
                createCategoryRequest.type());

        return new CategoryResponse(created.getId(), created.getName(), created.getType());
    }

    public List<CategoryResponse> list(@AuthenticationPrincipal User user) {
        return categoryService.listCategories(user)
                .stream()
                .map(category -> new CategoryResponse(category.getId(), category.getName(), category.getType()))
                .toList();
    }
}
