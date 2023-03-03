package com.chadwick.GoBankDB.service;

import com.chadwick.GoBankDB.dto.TransactionDTO;
import com.chadwick.GoBankDB.entity.Account;
import com.chadwick.GoBankDB.entity.Transaction;
import com.chadwick.GoBankDB.repository.AccountRepository;
import com.chadwick.GoBankDB.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    public Iterable<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    public List<Transaction> getAllAccountTransactions(long associatedAccountId){
        return transactionRepository.findAllAccountTransactions(associatedAccountId);
    }

    public TransactionDTO getTransactionByID(long id){
        Transaction transaction = transactionRepository.findById(id).get();
        return new TransactionDTO(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getTransactionDate(),
                transaction.getAmount(),
                transaction.getType()
        );
    }

    public long createTransaction(Transaction transaction, long associatedAccountId){
        transaction.setAssociatedAccountId(associatedAccountId);
        Transaction transaction1 = transactionRepository.save(transaction);
        // TODO: NEW STUFF BEGIN
        Account account = accountRepository.findById(associatedAccountId).get();
        List<Long> transactionIDs = account.getTransactionIDs();
        transactionIDs.add(transaction1.getId());
        account.setTransactionIDs(transactionIDs);
        accountRepository.save(account);
        // TODO: NEW STUFF END
        return transaction1.getId(); // when a new transaction is created, it will just return the ID
    }

// Transactions shouldn't be updatable
//    public Transaction updateTransaction (Transaction transaction){
//        return transactionRepository.save(transaction);
//    }

    public HttpStatus deleteTransaction(long id){
        transactionRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
