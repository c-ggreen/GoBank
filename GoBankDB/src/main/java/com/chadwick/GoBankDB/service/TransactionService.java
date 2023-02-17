package com.chadwick.GoBankDB.service;

import com.chadwick.GoBankDB.dto.TransactionDTO;
import com.chadwick.GoBankDB.entity.Transaction;
import com.chadwick.GoBankDB.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public Iterable<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    public TransactionDTO getTransactionByID(UUID id){
        Transaction transaction = transactionRepository.findById(id).get();
        return new TransactionDTO(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getTransactionDate(),
                transaction.getAmount(),
                transaction.getType()
        );
    }

    public UUID createTransaction(Transaction transaction){
        Transaction transaction1 = transactionRepository.save(transaction);
        return transaction1.getId(); // when a new transaction is created, it will just return the ID
    }

// Transactions shouldn't be updatable
//    public Transaction updateTransaction (Transaction transaction){
//        return transactionRepository.save(transaction);
//    }

    public HttpStatus deleteTransaction(UUID id){
        transactionRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
