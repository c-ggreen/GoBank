package com.chadwick.GoBankDB.controller;

import com.chadwick.GoBankDB.entity.Customer;
import com.chadwick.GoBankDB.service.CustomerService;
import com.chadwick.GoBankDB.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerByID(@PathVariable int id) {
        return ResponseHandler.apiResponse("Customer retrieved successfully.", customerService.getCustomerByID(id));
    }

    @GetMapping("/email")
    public ResponseEntity<Object> getCustomerByEmail(@RequestParam String email) {
        return ResponseHandler.apiResponse("Customer retrieved successfully.", customerService.getCustomerByEmail(email));
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<Object> getCustomerAccountIDs(@PathVariable int id) {
        return ResponseHandler.apiResponse("Customer accounts retrieved successfully.", customerService.getCustomerAccountIDs(id));
    }

    @PostMapping
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
        return ResponseHandler.apiResponse("Customer created successfully.", customerService.createCustomer(customer));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable int id, @RequestBody Customer updates) {
        return ResponseHandler.apiResponse("Customer updated successfully.", customerService.updateCustomer(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable int id) {
        return ResponseHandler.apiResponse("Customer deleted successfully.", customerService.deleteCustomer(id));
    }
}