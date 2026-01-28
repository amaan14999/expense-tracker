package com.amaan.expense_tracker.controller.dto;

import java.util.List;
import java.util.UUID;

public record BulkDeleteCategoriesResponse(
        List<UUID> deletedIds,
        List<UUID> blockedIds,
        List<UUID> notFoundIds
) {}
