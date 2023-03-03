package com.chadwick.GoBankDB.dto;

import java.time.Instant;

public record TransactionDTO(
        long id,
        String description,
        Instant transactionDate,
        String amount,
        String type
) {
}