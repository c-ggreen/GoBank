package com.chadwick.GoBankDB.controller;

import com.chadwick.GoBankDB.dto.AccountDTO;
import com.chadwick.GoBankDB.entity.Account;
import com.chadwick.GoBankDB.response.ResponseHandler;
import com.chadwick.GoBankDB.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<Object> getAccounts(){
        return ResponseHandler.apiResponse("Accounts retrieved successfully.",accountService.getAccounts());
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Object> getAccountByID(@PathVariable long accountId){
        return ResponseHandler.apiResponse("Account retrieved successfully.",accountService.getAccountByID(accountId));
    }

    @GetMapping("/customer/{accountOwnerID}")
    public ResponseEntity<Object> getAccountsByOwnerId(@PathVariable int accountOwnerID){
        return ResponseHandler.apiResponse("Accounts retrieved successfully.",accountService.getAccountsByOwnerId(accountOwnerID));
    }

    @PostMapping
    public ResponseEntity<Object> createAccount(@RequestBody Account account, @RequestParam int accountOwnerID){
        return ResponseHandler.apiResponse("Account created successfully.", accountService.createAccount(account, accountOwnerID));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateAccount(@PathVariable long id, @RequestBody Account updates){
        return ResponseHandler.apiResponse("Account has updated successfully.",accountService.updateAccount(id, updates));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Object> deleteAccount(@PathVariable long accountId){
        return ResponseHandler.apiResponse("Account deleted successfully.",accountService.deleteAccount(accountId));
    }

}