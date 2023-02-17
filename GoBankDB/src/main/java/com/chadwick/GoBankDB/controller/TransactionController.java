package com.chadwick.GoBankDB.controller;

import com.chadwick.GoBankDB.dto.TransactionDTO;
import com.chadwick.GoBankDB.entity.Transaction;
import com.chadwick.GoBankDB.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping
    public Iterable<Transaction> getTransactions(){
        return transactionService.getTransactions();
    }

    @GetMapping("/{id}")
    public TransactionDTO getTransactionByID(@PathVariable UUID id){
        return transactionService.getTransactionByID(id);
    }

    @PostMapping
    public UUID createTransaction(@RequestBody Transaction transaction){
        return transactionService.createTransaction(transaction);
    }
// Transactions shouldn't be updatable
//    @PatchMapping
//    public Transaction updateTransaction(@RequestBody Transaction transaction){
//        return transactionService.updateTransaction(transaction);
//    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteTransaction(@PathVariable UUID id){
        return transactionService.deleteTransaction(id);
    }
}
