package com.chadwick.GoBankDB.controller;

import com.chadwick.GoBankDB.dto.CustomerDTO;
import com.chadwick.GoBankDB.entity.Customer;
import com.chadwick.GoBankDB.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public Iterable<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/id")
    public CustomerDTO getCustomerByID(@RequestParam int id) {
        return customerService.getCustomerByID(id);
    }

    @GetMapping("/email")
    public CustomerDTO getCustomerByEmail(@RequestParam String email) {
        return customerService.getCustomerByEmail(email);
    }

    @GetMapping("/accounts")
    public List<Long> getCustomerAccountIDs(@RequestParam int id) {
        return customerService.getCustomerAccountIDs(id);
    }

    @PostMapping
    public int createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }


    @PatchMapping
    public CustomerDTO updateCustomer(@RequestParam int id, @RequestBody Customer updates) {
        return customerService.updateCustomer(id, updates);
    }

    @DeleteMapping
    public HttpStatus deleteCustomer(@RequestParam int id) {
        return customerService.deleteCustomer(id);
    }
}
