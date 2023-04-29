package com.chadwick.GoBankDB.service;

import com.chadwick.GoBankDB.dto.AccountDTO;
import com.chadwick.GoBankDB.entity.Account;
import com.chadwick.GoBankDB.entity.Customer;
import com.chadwick.GoBankDB.enums.AccountStatus;
import com.chadwick.GoBankDB.exception.InternalServerException;
import com.chadwick.GoBankDB.exception.NotFoundException;
import com.chadwick.GoBankDB.model.Name;
import com.chadwick.GoBankDB.repository.AccountRepository;
import com.chadwick.GoBankDB.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CustomerRepository customerRepository;

    public Iterable<Account> getAccounts(){
        return accountRepository.findAll();
    }

    public AccountDTO getAccountByID(long accountId){
        try{
            if (!accountRepository.existsById(accountId)){
                throw new NotFoundException("Account " + accountId + " does not exist.");
            }
            Account acc = accountRepository.findById(accountId).get();
            return new AccountDTO(
                    acc.getAccountId(),
                    acc.getAccountOwnerId(),
                    acc.getAccountOwnerName(),
                    acc.getBalance(),
                    acc.getAccountStatus(),
                    acc.getType(),
                    acc.getTransactionIDs()
            );
        } catch (Exception e){
            if(e instanceof NotFoundException){
                throw e;
            }
            throw new InternalServerException(e.getMessage());
        }
    }

    public List<AccountDTO> getAccountsByOwnerId(int accountOwnerId){
        try{
            List<AccountDTO> accountDTO = new ArrayList<>();
            List<Account> accounts = accountRepository.findAccountsByOwnerId(accountOwnerId);
            for (Account acc : accounts) {
                accountDTO.add(
                        new AccountDTO(
                            acc.getAccountId(),
                            acc.getAccountOwnerId(),
                            acc.getAccountOwnerName(),
                            acc.getBalance(),
                            acc.getAccountStatus(),
                            acc.getType(),
                            acc.getTransactionIDs()
                        )
                );
            }
            return accountDTO;
        } catch (Exception e){
            throw new InternalServerException(e.getMessage());
        }
    }

    public Map<String, Long> createAccount(Account account, int accountOwnerID){
        try{
            Map<String, Long> map = new HashMap<>();
            // when creating a new account, the customer ID is taken in to add to the account object and is used to query the customer to then add the account id to the list in the customer entity
            account.setAccountOwnerId(accountOwnerID);
            if (!customerRepository.existsById(accountOwnerID)){
                throw new NotFoundException("Account owner ID " + accountOwnerID + " not found");
            }
            Customer customer = customerRepository.findById(accountOwnerID).get();
            account.setAccountOwnerName(customer.getName()); // setting the account owner name field from the queried customer entity
            Account acc = accountRepository.save(account);
            List<Long> accountIDs = customer.getAccountIDs();
            accountIDs.add(acc.getAccountId());
            customer.setAccountIDs(accountIDs);
            customerRepository.save(customer);
            map.put("accountID",acc.getAccountId());
            return map;
        } catch (Exception e) {
            if(e instanceof NotFoundException){
                throw e;
            }
            throw new InternalServerException(e.getMessage());
        }
    }

    public Account updateAccount(long id, Account updates){
        try{
            if (!accountRepository.existsById(id)){
                throw new NotFoundException("Account " + id + " does not exist.");
            }
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
            if(updates.getAccountStatus() != null){
                account.setAccountStatus(updates.getAccountStatus());
                //check if the account status is now CLOSED, if so then account ID is removed from Customer accounts list
                if(updates.getAccountStatus() == AccountStatus.CLOSED){
                    Customer customer = customerRepository.findById(account.getAccountOwnerId()).get();
                    List<Long> accountIDs = customer.getAccountIDs();
                    accountIDs.remove(account.getAccountId());
                    customer.setAccountIDs(accountIDs);
                    customerRepository.save(customer);
                }
            }
            if(updates.getTransactionIDs() != null){
                account.setTransactionIDs(updates.getTransactionIDs());
            }

            return accountRepository.save(account);
        }catch (Exception e){
            if(e instanceof NotFoundException){
                throw e;
            }
            throw new InternalServerException(e.getMessage());
        }
    }

    public Map<String,Long> deleteAccount(long accountId){
        try{
            Map<String,Long> map = new HashMap<>();
            if (!accountRepository.existsById(accountId)){
                throw new NotFoundException("Account ID provided does not exist.");
            }
            accountRepository.deleteById(accountId);
            map.put("accountId", accountId);
            return map;

        }catch (Exception e){
            if(e instanceof NotFoundException){
                throw e;
            }
            throw new InternalServerException(e.getMessage());
        }
    }
}