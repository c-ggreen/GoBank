package com.chadwick.GoBankDB.service;

import com.chadwick.GoBankDB.dto.CustomerDTO;
import com.chadwick.GoBankDB.entity.Account;
import com.chadwick.GoBankDB.entity.Customer;
import com.chadwick.GoBankDB.exception.BadRequestException;
import com.chadwick.GoBankDB.exception.ForbiddenException;
import com.chadwick.GoBankDB.exception.NotFoundException;
import com.chadwick.GoBankDB.model.Address;
import com.chadwick.GoBankDB.model.Birthday;
import com.chadwick.GoBankDB.model.Name;
import com.chadwick.GoBankDB.repository.AccountRepository;
import com.chadwick.GoBankDB.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;

    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public CustomerDTO getCustomerByEmail(String email) {
        Customer target = customerRepository.findCustomerByEmail(email);
        if (target == null) {
            throw new NotFoundException("User not found.");
        }
        return new CustomerDTO(
                target.getCustomerId(),
                target.getEmail(),
                target.getName(),
                target.getGender(),
                target.getAddress(),
                target.getBirthday(),
                target.getRecipientList(),
                target.getAccountIDs()
        );
    }

    public CustomerDTO getCustomerByID(int id) {
        if (!customerRepository.findById(id).isPresent()) {
            throw new NotFoundException("Customer not found");
        }
        Customer customer = customerRepository.findById(id).get();
        return new CustomerDTO(
                customer.getCustomerId(),
                customer.getEmail(),
                customer.getName(),
                customer.getGender(),
                customer.getAddress(),
                customer.getBirthday(),
                customer.getRecipientList(),
                customer.getAccountIDs()
        );
    }

    public List<Long> getCustomerAccountIDs(int id) {
        List<Long> ids = customerRepository.findById(id).get().getAccountIDs();
        if (ids.isEmpty()) {
            throw new NotFoundException("No accounts available.");
        }
        return ids;
    }

    public Map<String,Integer> createCustomer(Customer customer) {
        Map<String,Integer> map = new HashMap<>();
        // the sql statement naturally returns null if no rows match the query, meaning that if it isn't null, a record with the email already exists
        if (customerRepository.findCustomerByEmail(customer.getEmail()) != null) {
            throw new ForbiddenException("Email " + customer.getEmail() + " already exists.");
        }
        if (customer.getPassword().length() < 8) {
            throw new BadRequestException("Password is to short");
        }
        Customer customer1 = customerRepository.save(customer);
        map.put("customerId", customer1.getCustomerId());
        return map;
    }

    public CustomerDTO updateCustomer(int id, Customer updates) {
        try {
            Customer customer = customerRepository.findById(id).get();
            if (updates.getEmail() != null) {
                    // checks to see if updated email already exists, if it does throw an exception
                if(customerRepository.findCustomerByEmail(updates.getEmail()) != null){
                    throw new BadRequestException("Email already exists");
                }
                customer.setEmail(updates.getEmail());
                // Will update account owner email if the user updates their email
                List<Long> accountIDs = customer.getAccountIDs(); // gets the account id's
                for (long item : accountIDs) { //iterates through the list of ids
                    Account account = accountRepository.findById(item).get(); //returns the account associated with the id in the iteration
                    account.setAccountOwnerEmail(customer.getEmail()); // updates the owner email
                    accountRepository.save(account); // saves the account
                }
            }
            if (updates.getPassword() != null) {
                customer.setPassword(updates.getPassword());
            }
            if (updates.getName() != null) {
                Name update = updates.getName();
                Name name = customer.getName();
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
                for (long accID : customer.getAccountIDs()) { //iterates through the list of ids
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
                customer.setGender(updates.getGender());
            }
            // to update address
            if (updates.getAddress() != null) {
                Address update = updates.getAddress();
                Address address = customer.getAddress();
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
                Birthday Birthday = customer.getBirthday();
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
                customer.setYearlyIncome(updates.getYearlyIncome());
            }
            if (updates.getMonthlyIncome() != null) {
                customer.setMonthlyIncome(updates.getMonthlyIncome());
            }
            if (updates.getPersonalDebt() != null) {
                customer.setPersonalDebt(updates.getPersonalDebt());
            }
            if (updates.getAccountIDs() != null) {
                customer.setAccountIDs(updates.getAccountIDs());
            }
            customerRepository.save(customer);
            return new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getEmail(),
                    customer.getName(),
                    customer.getGender(),
                    customer.getAddress(),
                    customer.getBirthday(),
                    customer.getRecipientList(),
                    customer.getAccountIDs()
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    public Map<String,Integer> deleteCustomer(int id) {
        try {
            Map<String,Integer> map = new HashMap<>();
            customerRepository.deleteById(id);
            map.put("customerID", id);
            return map;
        } catch (Exception e) {
            throw new NotFoundException("Customer not found.");
        }
    }
}