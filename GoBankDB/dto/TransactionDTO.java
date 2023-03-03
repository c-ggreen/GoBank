package com.chadwick.GoBankDB.dto;

import java.time.Instant;
import java.util.UUID;

public record TransactionDTO(
        long id,
        String description,
        Instant transactionDate,
        String amount,
        String type
) {
}
