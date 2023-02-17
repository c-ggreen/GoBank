package com.chadwick.GoBankDB.unit.Service;

import com.chadwick.GoBankDB.repository.AccountRepository;
import com.chadwick.GoBankDB.repository.CustomerRepository;
import com.chadwick.GoBankDB.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AccountRepository accountRepository;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService(customerRepository, accountRepository);
    }

    @Test
    void getUsers() {
        customerService.getCustomers();
        verify(customerRepository).findAll();
    }

    @Test
    @Disabled
    void getUserByID() {
    }

    @Test
    @Disabled
    void getUserAccounts() {
    }

    @Test
    @Disabled
    void getUserAccountByAccountID() {
    }

    @Test
    @Disabled
    void getAllTransactionsInUserAccount() {
    }

    @Test
    @Disabled
    void getTransactionsInUserAccountByID() {
    }

    @Test
    @Disabled
    void createUser() {
    }

    @Test
    @Disabled
    void createMultipleUsers() {
    }

    @Test
    @Disabled
    void updateUser() {
    }

    @Test
    @Disabled
    void deleteUser() {
    }
}