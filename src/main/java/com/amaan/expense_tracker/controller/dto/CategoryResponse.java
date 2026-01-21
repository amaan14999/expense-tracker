package com.amaan.expense_tracker.controller.dto;

import com.amaan.expense_tracker.domain.CategoryType;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name,
        CategoryType type
) {}
