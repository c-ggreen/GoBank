package com.chadwick.GoBankDB.service;

import com.chadwick.GoBankDB.dto.UserDTO;
import com.chadwick.GoBankDB.entity.Account;
import com.chadwick.GoBankDB.entity.Users;
import com.chadwick.GoBankDB.exception.ForbiddenException;
import com.chadwick.GoBankDB.exception.NotFoundException;
import com.chadwick.GoBankDB.model.Address;
import com.chadwick.GoBankDB.model.Birthday;
import com.chadwick.GoBankDB.model.Name;
import com.chadwick.GoBankDB.repository.AccountRepository;
import com.chadwick.GoBankDB.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

// TODO: Should my POST/PATCH/PUT requests return the data submitted?


@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    public Iterable<Users> getUsers() {
        return userRepository.findAll();
    }

    public Users getUserByEmail(String email) {
        Users target = userRepository.findUserByEmail(email);
        if (target == null) {
            throw new NotFoundException("User not found.");
        }
        return target;
    }

    public UserDTO getUserByID(UUID id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new NotFoundException("User not found");
        }
        Users user = userRepository.findById(id).get();
        return new UserDTO(
                user.getUserId(),
                user.getEmail(),
                user.getName(),
                user.getGender(),
                user.getAddress(),
                user.getBirthday(),
                user.getRecipientList(),
                user.getAccountIDs()
        );
    }

    public List<UUID> getUserAccountIDs(UUID id) {
        List<UUID> uuids = userRepository.findById(id).get().getAccountIDs();
        if (uuids.isEmpty()) {
            throw new NotFoundException("No accounts available.");
        }
        return uuids;
    }

    public Users createUser(Users user) {
        // the sql statement naturally returns null if no rows match the query, meaning that if it isn't null, a record with the email already exists
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new ForbiddenException("Email " + user.getEmail() + " already exists.");
        }
        return userRepository.save(user);

    }

    public Users updateUser(UUID id, Users updates) {
        try {
            Users user = userRepository.findById(id).get();
            if (updates.getEmail() != null) {
                user.setEmail(updates.getEmail());
                // Will update account owner email if the user updates their email
                List<UUID> accountIDs = user.getAccountIDs(); // gets the account id's
                for (UUID item : accountIDs) { //iterates through the list of ids
                    Account account = accountRepository.findById(item).get(); //returns the account associated with the id in the iteration
                    account.setAccountOwnerEmail(user.getEmail()); // updates the owner email
                    accountRepository.save(account); // saves the account
                }
            }
            if (updates.getPassword() != null) {
                user.setPassword(updates.getPassword());
            }
            if (updates.getName() != null) {
                Name update = updates.getName();
                Name name = user.getName();
                if (update.getFirst() != null) {
                    name.setFirst(update.getFirst());
                }
                if (update.getMiddle() != null) {
                    name.setMiddle(update.getMiddle());
                }
                if (update.getLast() != null) {
                    name.setLast(update.getLast());
                }

                // Will update account owner name if the user updates their name
                for (UUID accID : user.getAccountIDs()) { //iterates through the list of ids
                    Account account = accountRepository.findById(accID).get(); //returns the account associated with the id in the iteration
                    Name accName = account.getAccountOwnerName(); //temp variable to hold the current account holder name
                    if (update.getFirst() != null) {
                        accName.setFirst(update.getFirst());
                    }
                    if (update.getMiddle() != null) {
                        accName.setMiddle(update.getMiddle());
                    }
                    if (update.getLast() != null) {
                        accName.setLast(update.getLast());
                    }
                    account.setAccountOwnerName(accName); //setting the temp variable name as the account owner name
                    accountRepository.save(account); // saves the account
                }
            }

            if (updates.getGender() != null) {
                user.setGender(updates.getGender());
            }
            // to update address
            if (updates.getAddress() != null) {
                Address update = updates.getAddress();
                Address address = user.getAddress();
                if (update.getStreet() != null) {
                    address.setStreet(update.getStreet());
                }
                if (update.getUnit() != null) {
                    address.setUnit(update.getUnit());
                }
                if (update.getCity() != null) {
                    address.setCity(update.getCity());
                }
                if (update.getState() != null) {
                    address.setState(update.getState());
                }
                if (update.getZipCode() != null) {
                    address.setZipCode(update.getZipCode());
                }
                if (update.getCountry() != null) {
                    address.setCountry(update.getCountry());
                }
            }
            // to update birthday
            if (updates.getBirthday() != null) {
                Birthday update = updates.getBirthday();
                Birthday Birthday = user.getBirthday();
                if (update.getDay() != null) {
                    Birthday.setDay(update.getDay());
                }
                if (update.getMonth() != null) {
                    Birthday.setMonth(update.getMonth());
                }
                if (update.getYear() != null) {
                    Birthday.setYear(update.getYear());
                }
            }
            if (updates.getYearlyIncome() != null) {
                user.setYearlyIncome(updates.getYearlyIncome());
            }
            if (updates.getMonthlyIncome() != null) {
                user.setMonthlyIncome(updates.getMonthlyIncome());
            }
            if (updates.getPersonalDebt() != null) {
                user.setPersonalDebt(updates.getPersonalDebt());
            }
            if (updates.getAccountIDs() != null) {
                user.setAccountIDs(updates.getAccountIDs());
            }
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    public HttpStatus deleteUser(UUID id) {
        try {
            userRepository.deleteById(id);
            return new ResponseStatusException(HttpStatus.OK).getStatus();
        } catch (Exception e) {
            throw new NotFoundException("User not found.");
        }
    }
}
