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

    @GetMapping("/{id}")
    public CustomerDTO getCustomerByID(@PathVariable UUID id) {
        return customerService.getCustomerByID(id);
    }

    @GetMapping("/email/{email}")
    public CustomerDTO getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    @GetMapping("/{id}/accounts")
    public List<UUID> getCustomerAccountIDs(@PathVariable UUID id) {
        return customerService.getCustomerAccountIDs(id);
    }

    @PostMapping
    public UUID createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }


    @PatchMapping("/{id}")
    public Customer updateCustomer(@PathVariable UUID id, @RequestBody Customer updates) {
        return customerService.updateCustomer(id, updates);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteCustomer(@PathVariable UUID id) {
        return customerService.deleteCustomer(id);
    }
}
