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
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public Iterable<Users> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{email}")
    public Users getUserByID(@PathVariable String email) {
        return userService.getUserByID(email);
    }

    @GetMapping("/{email}/accounts")
    public List<Account> getUserAccounts(@PathVariable String email) {
        return userService.getUserAccounts(email);
    }

    @GetMapping("/{email}/accounts/{accountId}")
    public Account getUserAccountByAccountID(@PathVariable String email, @PathVariable String accountId) {
        return userService.getUserAccountByAccountID(email, accountId);
    }

    @GetMapping("/{email}/accounts/{accountId}/transactions")
    public List<Transaction> getAllTransactionsInUserAccount(@PathVariable String email, @PathVariable String accountId){
        return userService.getAllTransactionsInUserAccount(email, accountId);
    }

    @GetMapping("/{email}/accounts/{accountId}/transactions/{transactionId}")
    public Transaction getTransactionInUserAccountByID(@PathVariable String email, @PathVariable String accountId, @PathVariable Long transactionId){
        return userService.getTransactionsInUserAccountByID(email, accountId, transactionId);
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    @PatchMapping
    public Users updateUser(@RequestBody Users user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{email}")
    public HttpStatus deleteUser(@PathVariable String email) {
        return userService.deleteUser(email);
    }
}
