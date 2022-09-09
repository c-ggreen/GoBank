package com.chadwick.GoBankDB.Service;

import com.chadwick.GoBankDB.Entity.Account;
import com.chadwick.GoBankDB.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Iterable<Account> getAccounts(){
        return accountRepository.findAll();
    }

    public Account getAccountByID(UUID id){
        return accountRepository.findById(id).get();
    }

    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    public Account updateAccount(Account account){
        return accountRepository.save(account);
    }

    public HttpStatus deleteAccount(UUID id){
        accountRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
