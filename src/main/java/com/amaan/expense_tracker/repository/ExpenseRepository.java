package com.amaan.expense_tracker.repository;

import com.amaan.expense_tracker.domain.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    Page<Expense> findByUser_Id(UUID id, Pageable pageable);
    @Query("""
           select distinct e.category.id
           from Expense e
           where e.user.id = :userId
             and e.category.id in :categoryIds
           """)
    List<UUID> findCategoryIdsInUse(@Param("userId") UUID userId,
                                    @Param("categoryIds") Collection<UUID> categoryIds);
}
