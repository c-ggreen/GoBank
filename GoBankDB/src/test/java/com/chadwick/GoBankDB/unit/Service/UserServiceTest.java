package com.chadwick.GoBankDB.unit.Service;

import com.chadwick.GoBankDB.Repository.UserRepository;
import com.chadwick.GoBankDB.Service.UserService;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void getUsers() {
        userService.getUsers();
        verify(userRepository).findAll();
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