package com.chadwick.GoBankDB.Controller;

import com.chadwick.GoBankDB.Entity.Account;
import com.chadwick.GoBankDB.Entity.Transaction;
import com.chadwick.GoBankDB.Entity.Users;
import com.chadwick.GoBankDB.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public Iterable<Users> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public Users getUserByID(@PathVariable UUID id) {
        return userService.getUserByID(id);
    }

    @GetMapping("/{id}/accounts")
    public List<Account> getUserAccounts(@PathVariable UUID id) {
        return userService.getUserAccounts(id);
    }

    @GetMapping("/{id}/accounts/{accountId}")
    public Account getUserAccountByAccountID(@PathVariable UUID id, @PathVariable String accountId) {
        return userService.getUserAccountByAccountID(id, accountId);
    }

    @GetMapping("/{id}/accounts/{accountId}/transactions")
    public List<Transaction> getAllTransactionsInUserAccount(@PathVariable UUID id, @PathVariable String accountId){
        return userService.getAllTransactionsInUserAccount(id, accountId);
    }

    @GetMapping("/{id}/accounts/{accountId}/transactions/{transactionId}")
    public Transaction getTransactionInUserAccountByID(@PathVariable UUID id, @PathVariable String accountId, @PathVariable Long transactionId){
        return userService.getTransactionsInUserAccountByID(id, accountId, transactionId);
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    // need to fix related method in service class
//    @PostMapping("/multiple")
//    public Iterable<Users> createMultipleUsers(@RequestBody List<Users> usersList){
//        return userService.createMultipleUsers(usersList);
//    }


    @PatchMapping("/update/{id}")
    public Users updateUser(@PathVariable UUID id, @RequestBody Users updates) {
        return userService.updateUser(id, updates);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }
}
