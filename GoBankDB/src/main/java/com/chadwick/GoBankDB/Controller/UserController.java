package com.chadwick.GoBankDB.Controller;

import com.chadwick.GoBankDB.Entity.Account;
import com.chadwick.GoBankDB.Entity.Users;
import com.chadwick.GoBankDB.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public Iterable<Users> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{email}")
    public Users getUserByID(@PathVariable String email) {
        return userService.getUserByID(email);
    }

    @GetMapping("/{email}/accounts")
    public List<Account> getUserAccounts(@PathVariable String email) {
        return userService.getUserByID(email).getAccounts();
    }

    @GetMapping("/{email}/accounts/{id}")
    public Account getUserAccountByAccountID(@PathVariable String email, @PathVariable String id) {
        List<Account> accounts = userService.getUserByID(email).getAccounts();
        for (Account account : accounts) {
            if (account.getId().toString().equals(id)) {
                return account;
            }
        }
        throw new Error("Account not found.");
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    @PatchMapping
    public Users updateUser(@RequestBody Users user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{email}")
    public HttpStatus deleteUser(@PathVariable String email) {
        return userService.deleteUser(email);
    }
}
