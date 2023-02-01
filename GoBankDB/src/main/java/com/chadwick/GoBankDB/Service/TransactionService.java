package com.chadwick.GoBankDB.Service;

import com.chadwick.GoBankDB.Entity.Transaction;
import com.chadwick.GoBankDB.Repository.TransactionRepository;
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

    public Transaction getTransactionByID(UUID id){
        return transactionRepository.findById(id).get();
    }

    public Transaction createTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction (Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public HttpStatus deleteTransaction(UUID id){
        transactionRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
