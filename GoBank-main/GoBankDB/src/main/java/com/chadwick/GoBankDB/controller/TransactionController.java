package com.chadwick.GoBankDB.controller;

import com.chadwick.GoBankDB.dto.TransactionDTO;
import com.chadwick.GoBankDB.entity.Transaction;
import com.chadwick.GoBankDB.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/account/{associatedAccountId}")
    public List<Transaction> getAllAccountTransactions(@PathVariable long associatedAccountId){
        return transactionService.getAllAccountTransactions(associatedAccountId);
    }

    @GetMapping("/{id}")
    public TransactionDTO getTransactionByID(@PathVariable long id){
        return transactionService.getTransactionByID(id);
    }

    @PostMapping
    public long createTransaction(@RequestBody Transaction transaction, @RequestParam long associatedAccountId){
        return transactionService.createTransaction(transaction, associatedAccountId);
    }
// Transactions shouldn't be updatable
//    @PatchMapping
//    public Transaction updateTransaction(@RequestBody Transaction transaction){
//        return transactionService.updateTransaction(transaction);
//    }

    @DeleteMapping
    public HttpStatus deleteTransaction(@RequestParam long id){
        return transactionService.deleteTransaction(id);
    }
}
