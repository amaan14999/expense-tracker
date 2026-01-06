package com.amaan.expense_tracker.repository;

import com.amaan.expense_tracker.domain.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    Page<Expense> findByUser_Id(UUID id, Pageable pageable);
}
