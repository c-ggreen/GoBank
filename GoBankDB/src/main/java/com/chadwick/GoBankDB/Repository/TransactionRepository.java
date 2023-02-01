package com.chadwick.GoBankDB.Repository;

import com.chadwick.GoBankDB.Entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TransactionRepository extends CrudRepository<Transaction, UUID> {
}
