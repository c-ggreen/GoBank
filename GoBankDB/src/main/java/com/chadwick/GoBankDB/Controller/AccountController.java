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

    @GetMapping("/{id}")
    public Account getAccountByID(@PathVariable UUID id){
        return accountService.getAccountByID(id);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @PatchMapping
    public Account updateAccount(@RequestBody Account account){
        return accountService.updateAccount(account);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteAccount(@PathVariable UUID id){
        return accountService.deleteAccount(id);
    }

}
