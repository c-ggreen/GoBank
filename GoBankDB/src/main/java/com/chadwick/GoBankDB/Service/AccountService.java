package com.chadwick.GoBankDB.Service;

import com.chadwick.GoBankDB.Entity.Account;
import com.chadwick.GoBankDB.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Iterable<Account> getAccounts(){
        return accountRepository.findAll();
    }

    public Account getAccountByID(Long id){
        return accountRepository.findById(id).get();
    }

    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    public Account updateAccount(Account account){
        return accountRepository.save(account);
    }

    public HttpStatus deleteAccount(Long id){
        accountRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
