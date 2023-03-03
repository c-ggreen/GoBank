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
import java.util.UUID;

// TODO: Build out Account Service and Controller like what was done for User; need to identify use cases though
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
        account.setAccountOwnerId(accountOwnerID);
        Account acc = accountRepository.save(account);
        // TODO: NEW STUFF BEGIN
        Customer customer = customerRepository.findById(accountOwnerID).get();
        List<Long> accountIDs = customer.getAccountIDs();
        accountIDs.add(acc.getAccountId());
        customer.setAccountIDs(accountIDs);
        customerRepository.save(customer);
        // TODO: NEW STUFF END
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
