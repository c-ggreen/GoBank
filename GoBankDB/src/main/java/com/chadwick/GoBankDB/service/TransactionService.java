package com.chadwick.GoBankDB.service;

import com.chadwick.GoBankDB.dto.TransactionDTO;
import com.chadwick.GoBankDB.entity.Account;
import com.chadwick.GoBankDB.entity.Transaction;
import com.chadwick.GoBankDB.exception.InternalServerException;
import com.chadwick.GoBankDB.exception.NotFoundException;
import com.chadwick.GoBankDB.repository.AccountRepository;
import com.chadwick.GoBankDB.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    //keeping this method as is for now to get full return value of transactions; won't be used in client side calls
    public Iterable<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    public List<TransactionDTO> getAllAccountTransactions(long associatedAccountId){
        try{
            List<TransactionDTO> transactionsDTO = new ArrayList<>();
            List<Transaction> transactions = transactionRepository.findAllAccountTransactions(associatedAccountId);
            for (Transaction transaction: transactions) {
                transactionsDTO.add(
                        new TransactionDTO(
                            transaction.getId(),
                            transaction.getDescription(),
                            transaction.getTransactionDate(),
                            transaction.getAmount(),
                            transaction.getType()
                        )
                );
            }
            return transactionsDTO;
        } catch (Exception e){
            throw new InternalServerException(e.getMessage());
        }
    }

    public TransactionDTO getTransactionByID(long id){
        try{
            if(!transactionRepository.existsById(id)){
                throw new NotFoundException("Transaction with ID " + id + " not found.");
            }
            Transaction transaction = transactionRepository.findById(id).get();
            return new TransactionDTO(
                    transaction.getId(),
                    transaction.getDescription(),
                    transaction.getTransactionDate(),
                    transaction.getAmount(),
                    transaction.getType()
            );
        }catch (Exception e){
            if(e instanceof NotFoundException){
                throw e;
            }
            throw new InternalServerException(e.getMessage());
        }
    }

    public Map<String, Long> createTransaction(Transaction transaction, long associatedAccountId){
        try {
            Map<String, Long> map = new HashMap<>();
            // when creating a new transaction, the associatedAccountId is taken in to add to the transaction object and is used to query the associated account to then add the transaction id to the list in the account entity
            transaction.setAssociatedAccountId(associatedAccountId);
            Transaction transaction1 = transactionRepository.save(transaction);
            if (!accountRepository.existsById(associatedAccountId)){
                throw new NotFoundException("Account " + associatedAccountId + " does not exist.");
            }
            Account account = accountRepository.findById(associatedAccountId).get();
            List<Long> transactionIDs = account.getTransactionIDs();
            transactionIDs.add(transaction1.getId());
            account.setTransactionIDs(transactionIDs);
            accountRepository.save(account);
            map.put("transactionID", transaction1.getId());
            return map; // when a new transaction is created, it will just return the ID
        } catch (Exception e) {
            if(e instanceof NotFoundException){
                throw e;
            }
            throw new InternalServerException(e.getMessage());
        }
    }

    public Map<String, Long> deleteTransaction(long id){
        try{
            if(!transactionRepository.existsById(id)){
                throw new NotFoundException("Transaction " + id + " not found.");
            }
            Map<String, Long> map = new HashMap<>();
            transactionRepository.deleteById(id);
            map.put("transactionID", id);
            return map;
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}