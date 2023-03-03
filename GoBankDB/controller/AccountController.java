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
    public Account getAccountByID(@PathVariable long accountId){
        return accountService.getAccountByID(accountId);
    }

    @GetMapping("/customer/{accountOwnerID}")
    public List<Account> getAccountsByOwnerId(@PathVariable int accountOwnerID){
        return accountService.getAccountsByOwnerId(accountOwnerID);
    }

    @PostMapping
    public AccountDTO createAccount(@RequestBody Account account, @RequestParam int accountOwnerID){
        return accountService.createAccount(account, accountOwnerID);
    }

    @PatchMapping
    public Account updateAccount(@RequestParam long id, @RequestBody Account updates){
        return accountService.updateAccount(id, updates);
    }

    @DeleteMapping
    public HttpStatus deleteAccount(@RequestParam long accountId){
        return accountService.deleteAccount(accountId);
    }

}
