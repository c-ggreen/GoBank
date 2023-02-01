package com.chadwick.GoBankDB.Controller;

import com.chadwick.GoBankDB.Entity.Account;
import com.chadwick.GoBankDB.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/account")
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

    @PostMapping
    public Account createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @PatchMapping("update/{id}")
    public Account updateAccount(@PathVariable UUID id, @RequestBody Account updates){
        return accountService.updateAccount(id, updates);
    }

    @DeleteMapping("/{accountId}")
    public HttpStatus deleteAccount(@PathVariable UUID accountId){
        return accountService.deleteAccount(accountId);
    }

}
