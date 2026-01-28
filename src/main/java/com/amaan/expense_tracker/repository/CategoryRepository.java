package com.amaan.expense_tracker.repository;

import com.amaan.expense_tracker.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByUser_IdOrderByNameAsc(UUID userId);
    boolean existsByUser_IdAndNameIgnoreCase(UUID userId, String name);
    Optional<Category> findByIdAndUser_Id(UUID id, UUID userId);
    List<Category> findByUser_IdAndIdIn(UUID userId, Collection<UUID> ids);
}
