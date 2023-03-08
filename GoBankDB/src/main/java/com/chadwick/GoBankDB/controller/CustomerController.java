package com.chadwick.GoBankDB.controller;

import com.chadwick.GoBankDB.entity.Customer;
import com.chadwick.GoBankDB.service.CustomerService;
import com.chadwick.GoBankDB.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<Object> getCustomers() {
        return ResponseHandler.apiResponse("Customer list retrieved successfully.", customerService.getCustomers());
    }

    @GetMapping("/id")
    public ResponseEntity<Object> getCustomerByID(@RequestParam int id) {
        return ResponseHandler.apiResponse("Customer retrieved successfully.", customerService.getCustomerByID(id));
    }

    @GetMapping("/email")
    public ResponseEntity<Object> getCustomerByEmail(@RequestParam String email) {
        return ResponseHandler.apiResponse("Customer retrieved successfully.", customerService.getCustomerByEmail(email));
    }

    @GetMapping("/accounts")
    public ResponseEntity<Object> getCustomerAccountIDs(@RequestParam int id) {
        return ResponseHandler.apiResponse("Customer accounts retrieved successfully.", customerService.getCustomerAccountIDs(id));
    }

    @PostMapping
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
        return ResponseHandler.apiResponse("Customer created successfully", customerService.createCustomer(customer));
    }


    @PatchMapping
    public ResponseEntity<Object> updateCustomer(@RequestParam int id, @RequestBody Customer updates) {
        return ResponseHandler.apiResponse("Customer updated successfully", customerService.updateCustomer(id, updates));
    }

    @DeleteMapping
    public HttpStatus deleteCustomer(@RequestParam int id) {
        return customerService.deleteCustomer(id);
    }
}