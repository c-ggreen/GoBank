package com.chadwick.GoBankDB.controller;

import com.chadwick.GoBankDB.dto.AccountDTO;
import com.chadwick.GoBankDB.entity.Account;
import com.chadwick.GoBankDB.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping
    public Iterable<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @GetMapping("/{accountId}")
    public Account getAccountByID(@PathVariable UUID accountId){
        return accountService.getAccountByID(accountId);
    }

    @GetMapping("/user/{accountOwnerID}")
    public List<Account> getAccountsByOwnerId(@PathVariable UUID accountOwnerID){
        return accountService.getAccountsByOwnerId(accountOwnerID);
    }

    @PostMapping
    public AccountDTO createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @PatchMapping("/{id}")
    public Account updateAccount(@PathVariable UUID id, @RequestBody Account updates){
        return accountService.updateAccount(id, updates);
    }

    @DeleteMapping("/{accountId}")
    public HttpStatus deleteAccount(@PathVariable UUID accountId){
        return accountService.deleteAccount(accountId);
    }

}
