package com.chadwick.GoBankDB.dto;

import com.chadwick.GoBankDB.enums.TransactionType;

import java.time.Instant;

public record TransactionDTO(
        long id,
        String description,
        Instant transactionDate,
        String amount,
        TransactionType type
) {
}