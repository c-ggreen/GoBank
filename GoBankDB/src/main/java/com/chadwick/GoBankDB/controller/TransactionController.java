package com.chadwick.GoBankDB.controller;

import com.chadwick.GoBankDB.dto.TransactionDTO;
import com.chadwick.GoBankDB.entity.Transaction;
import com.chadwick.GoBankDB.response.ResponseHandler;
import com.chadwick.GoBankDB.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/account")
    public ResponseEntity<Object> getAllAccountTransactions(@RequestParam long associatedAccountId){
        return ResponseHandler.apiResponse("Transactions with the same associated account returned successfully",transactionService.getAllAccountTransactions(associatedAccountId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTransactionByID(@PathVariable long id){
        return ResponseHandler.apiResponse("Transaction retrieved successfully.", transactionService.getTransactionByID(id));
    }

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody Transaction transaction, @RequestParam long associatedAccountId){
        return ResponseHandler.apiResponse("Transaction created successfully.", transactionService.createTransaction(transaction, associatedAccountId));
    }
// Transactions shouldn't be updatable
//    @PatchMapping
//    public Transaction updateTransaction(@RequestBody Transaction transaction){
//        return transactionService.updateTransaction(transaction);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransaction(@PathVariable long id){
        return ResponseHandler.apiResponse("Transaction deleted successfully.",transactionService.deleteTransaction(id));
    }
}