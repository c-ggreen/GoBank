package com.chadwick.GoBankDB.dto;

import com.chadwick.GoBankDB.model.Name;

import java.util.List;
import java.util.UUID;

public record AccountDTO(
        long accountId,
        int accountOwnerId,
        Name accountOwnerName,
        String balance,
        List<Long> transactionIds
) {
}
