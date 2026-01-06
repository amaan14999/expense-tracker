package com.amaan.expense_tracker.repository;

import com.amaan.expense_tracker.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByUser_Id(UUID id);
}
