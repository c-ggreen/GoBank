package com.chadwick.GoBankDB.dto;

import com.chadwick.GoBankDB.enums.AccountStatus;
import com.chadwick.GoBankDB.model.Name;

import java.util.List;

public record AccountDTO(
        long accountId,
        int accountOwnerId,
        Name accountOwnerName,
        String balance,
        AccountStatus accountStatus,
        List<Long> transactionIds
) {
}