package com.chadwick.GoBankDB.service;

import com.chadwick.GoBankDB.dto.AccountDTO;
import com.chadwick.GoBankDB.entity.Account;
import com.chadwick.GoBankDB.entity.Customer;
import com.chadwick.GoBankDB.model.Name;
import com.chadwick.GoBankDB.repository.AccountRepository;
import com.chadwick.GoBankDB.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CustomerRepository customerRepository;

    public Iterable<Account> getAccounts(){
        return accountRepository.findAll();
    }

    public Account getAccountByID(long accountId){
        return accountRepository.findById(accountId).get();
    }

    public List<Account> getAccountsByOwnerId(int accountOwnerId){
        return accountRepository.findAccountsByOwnerId(accountOwnerId);
    }

    public AccountDTO createAccount(Account account, int accountOwnerID){
        // when creating a new account, the customer ID is taken in to add to the account object and is used to query the customer to then add the account id to the list in the customer entity
        account.setAccountOwnerId(accountOwnerID);
        Account acc = accountRepository.save(account);
        Customer customer = customerRepository.findById(accountOwnerID).get();
        List<Long> accountIDs = customer.getAccountIDs();
        accountIDs.add(acc.getAccountId());
        customer.setAccountIDs(accountIDs);
        customerRepository.save(customer);
        return new AccountDTO(
                acc.getAccountId(),
                acc.getAccountOwnerId(),
                acc.getAccountOwnerName(),
                acc.getBalance(),
                acc.getTransactionIDs()
        );
    }

    public Account updateAccount(long id, Account updates){
        try{
            Account account = accountRepository.findById(id).get();

            if(updates.getAccountOwnerName() != null){
                Name update = updates.getAccountOwnerName();
                Name name = account.getAccountOwnerName();
                if(update.getFirst() != null){
                    name.setFirst(update.getFirst());
                }
                if(update.getMiddle() != null){
                    name.setMiddle(update.getMiddle());
                }
                if(update.getLast() != null){
                    name.setLast(update.getLast());
                }
            }
            if(updates.getAccountOwnerEmail() != null){
                account.setAccountOwnerEmail(updates.getAccountOwnerEmail());
            }
            if(updates.getAccountNickName() != null){
                account.setAccountNickName(updates.getAccountNickName());
            }
            if(updates.getBalance() != null){
                account.setBalance(updates.getBalance());
            }
            if(updates.getTransactionIDs() != null){
                account.setTransactionIDs(updates.getTransactionIDs());
            }

            return accountRepository.save(account);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    public HttpStatus deleteAccount(long accountId){
        accountRepository.deleteById(accountId);
        return HttpStatus.OK;
    }
}