package com.chadwick.GoBankDB.Service;

import com.chadwick.GoBankDB.Entity.Account;
import com.chadwick.GoBankDB.Entity.Transaction;
import com.chadwick.GoBankDB.Entity.Users;
import com.chadwick.GoBankDB.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Iterable<Users> getUsers(){
        return userRepository.findAll();
    }

    public Users getUserByID(String email){
        return userRepository.findById(email).get();
    }

    public List<Account> getUserAccounts(String email) {
        return userRepository.findById(email).get().getAccounts();
    }
    public Account getUserAccountByAccountID(String email, String accountId) {
        List<Account> accounts = getUserAccounts(email);
        for (Account account : accounts) {
            if (account.getAccountId().toString().equals(accountId)) {
                return account;
            }
        }
        throw new Error("Account not found.");
    }

    public List<Transaction> getAllTransactionsInUserAccount(String email, String accountId){
        List<Transaction> transactions;
        List<Account> accounts = userRepository.findById(email).get().getAccounts();
        for (Account account : accounts) {
            if (account.getAccountId().toString().equals(accountId)) {
                transactions = account.getTransactions();
                return transactions;
            }
        }
        throw new Error("Error retrieving transaction list");
    }

    public Transaction getTransactionsInUserAccountByID(String email, String accountId, Long transactionId){
        List<Transaction> transactions = getAllTransactionsInUserAccount(email, accountId);
        for (Transaction transaction : transactions){
            if(transaction.getId().equals(transactionId)){
                return transaction;
            }
        }
        throw new Error("Error retrieving transaction");
    }

    public Users createUser(Users user){
        return userRepository.save(user);
    }

    public Iterable<Users> createMultipleUsers(List<Users> users){
        return userRepository.saveAll(users);
    }

    public Users updateUser(Users user){
        return userRepository.save(user);
    }

    public HttpStatus deleteUser(String email){
        userRepository.deleteById(email);
        return HttpStatus.OK;
    }
}
