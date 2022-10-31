package com.chadwick.GoBankDB.Service;

import com.chadwick.GoBankDB.Entity.Account;
import com.chadwick.GoBankDB.Entity.Transaction;
import com.chadwick.GoBankDB.Entity.Users;
import com.chadwick.GoBankDB.Model.Address;
import com.chadwick.GoBankDB.Model.Birthdate;
import com.chadwick.GoBankDB.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Iterable<Users> getUsers() {
        return userRepository.findAll();
    }

    public Users getUserByID(UUID id) {
        try{
            return userRepository.findById(id).get();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }
    }

    public List<Account> getUserAccounts(UUID id) {
        return userRepository.findById(id).get().getAccounts();
    }

    public Account getUserAccountByAccountID(UUID id, String accountId) {
        try{
            List<Account> accounts = getUserAccounts(id);
            Account target = null;
            for (Account account : accounts) {
                if (account.getAccountId().toString().equals(accountId)) {
                    target = account;
                    break;
                }
                else {
                    throw new Exception("Account not found.");
                }
            }
            return target;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    public List<Transaction> getAllTransactionsInUserAccount(UUID id, String accountId) {
        try{
            List<Transaction> transactions = null;
            List<Account> accounts = userRepository.findById(id).get().getAccounts();
            for (Account account : accounts) {
                if (account.getAccountId().toString().equals(accountId)) {
                    transactions = account.getTransactions();
                }
                else {
                    throw new Exception("Error retrieving transaction list");
                }
            }
            return transactions;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    public Transaction getTransactionsInUserAccountByID(UUID id, String accountId, Long transactionId) {
        try{
            List<Transaction> transactions = getAllTransactionsInUserAccount(id, accountId);
            Transaction target = null;
            for (Transaction transaction : transactions) {
                if (transaction.getId().equals(transactionId)) {
                    target = transaction;
                }
                else {
                    throw new Exception("Error retrieving transaction");
                }
            }
            return target;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    public Users createUser(Users user) {
        Iterable<Users> usersList = getUsers();
            // so we need to get the email of the proposed user, and check to see if that email is present in our db
            // not using the email as a primary key because it needs to be updatable
            // could possibly retrieve the list of all users, and iterate through each entity, comparing the emails in each iteration
            // This solution wouldn't be a problem for a small amount of users, but would be for millions
            // using composite primary keys may help, but I don't think they're updatable, will need to do further research
        try {
            for (Users item: usersList) {
                // ignores case sensitivity
                if (item.getEmail().equalsIgnoreCase(user.getEmail())) {
                    throw new Exception("Email " + user.getEmail() + " already exists.");
                }
            }
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, null, e);
        }
    }

    // should be an internal facing method, no use for it in the external
//    public Iterable<Users> createMultipleUsers(List<Users> users) {
//        List<Users> usersList = new ArrayList<>();
//        List<String> badEntryList = new ArrayList<>();
//        for (Users user : users) {
//            try {
//                String email = user.getEmail();
//                if (userRepository.findById(email).isPresent()) {
//                    badEntryList.add(email);
////                    throw new Exception("Email " + email + " already exists.");
//                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Email " + email + " already exists.");
//                }
//                usersList.add(user);
//            } catch (ResponseStatusException e) {
//                System.out.println("Response Status Exception: "+e);
//            }
//        }
//        System.out.println("Bad entries:" + badEntryList);
//        return userRepository.saveAll(usersList);
//    }


    public Users updateUser(UUID id, Users updates){
        try{
            Users user = getUserByID(id);
            if(updates.getEmail() != null){
                user.setEmail(updates.getEmail());
                // Will update account owner email if the user updates their email
                for (Account item: user.getAccounts()) {
                    item.setAccountOwnerEmail(user.getEmail());
                }
            }
            if(updates.getPassword() != null){
                user.setPassword(updates.getPassword());
            }
            if(updates.getFirstName() != null){
                user.setFirstName(updates.getFirstName());
            }
            if(updates.getMiddleName() != null){
                user.setMiddleName(updates.getMiddleName());
            }
            if(updates.getLastName() != null){
                user.setLastName(updates.getLastName());
            }
            // Will update account owner name if the user updates their name
            for (Account item: user.getAccounts()) {
                item.setAccountOwnerName(user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
            }

            if(updates.getGender() != null){
                user.setGender(updates.getGender());
            }
            // to update address
            if(updates.getAddress() != null){
                Address update = updates.getAddress();
                Address address = user.getAddress();
                if(update.getStreet() != null){
                    address.setStreet(update.getStreet());
                }
                if(update.getUnit() != null){
                    address.setUnit(update.getUnit());
                }
                if(update.getCity() != null){
                    address.setCity(update.getCity());
                }
                if(update.getState() != null){
                    address.setState(update.getState());
                }
                if(update.getZipCode() != null){
                    address.setZipCode(update.getZipCode());
                }
                if(update.getCountry() != null){
                    address.setCountry(update.getCountry());
                }
            }
            // to update birthday
            if(updates.getBirthdate() != null){
                Birthdate update = updates.getBirthdate();
                Birthdate birthdate = user.getBirthdate();
                if(update.getDay() != null){
                    birthdate.setDay(update.getDay());
                }
                if(update.getMonth() != null){
                    birthdate.setMonth(update.getMonth());
                }
                if(update.getYear() != null){
                    birthdate.setYear(update.getYear());
                }
            }
            if(updates.getYearlyIncome() != null){
                user.setYearlyIncome(updates.getYearlyIncome());
            }
            if(updates.getMonthlyIncome() != null){
                user.setMonthlyIncome(updates.getMonthlyIncome());
            }
            if(updates.getPersonalDebt() != null){
                user.setPersonalDebt(updates.getPersonalDebt());
            }
            return userRepository.save(user);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    public HttpStatus deleteUser(UUID id) {
        try{
            userRepository.deleteById(id);
            return new ResponseStatusException(HttpStatus.OK).getStatus();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.", e);
        }
    }
}
