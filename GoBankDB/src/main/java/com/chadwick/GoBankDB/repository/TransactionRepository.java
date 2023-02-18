package com.chadwick.GoBankDB.repository;

import com.chadwick.GoBankDB.entity.Customer;
import com.chadwick.GoBankDB.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends CrudRepository<Transaction, UUID> {
    @Query("SELECT t FROM Transaction t WHERE t.associatedAccountId = :associatedAccountId")
    List<Transaction> findAllAccountTransactions(
            @Param("associatedAccountId") UUID associatedAccountId
    );
}
