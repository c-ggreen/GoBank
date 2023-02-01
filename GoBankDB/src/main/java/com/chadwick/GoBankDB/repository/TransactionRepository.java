package com.chadwick.GoBankDB.repository;

import com.chadwick.GoBankDB.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TransactionRepository extends CrudRepository<Transaction, UUID> {
}
