package com.chadwick.GoBankDB.Controller;

import com.chadwick.GoBankDB.Entity.Transaction;
import com.chadwick.GoBankDB.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping
    public Iterable<Transaction> getTransactions(){
        return transactionService.getTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionByID(@PathVariable Long id){
        return transactionService.getTransactionByID(id);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return transactionService.createTransaction(transaction);
    }

    @PatchMapping
    public Transaction updateTransaction(@RequestBody Transaction transaction){
        return transactionService.updateTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteTransaction(@PathVariable Long id){
        return transactionService.deleteTransaction(id);
    }
}
