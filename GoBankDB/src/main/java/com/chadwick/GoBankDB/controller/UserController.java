package com.chadwick.GoBankDB.controller;

import com.chadwick.GoBankDB.entity.Users;
import com.chadwick.GoBankDB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public Iterable<Users> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public Users getUserByID(@PathVariable UUID id) {
        return userService.getUserByID(id);
    }
    @GetMapping("/e/{email}")
    public Users getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/{id}/accounts")
    public List<UUID> getUserAccounts(@PathVariable UUID id) {
        return userService.getUserAccountIDs(id);
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }


    @PatchMapping("/update/{id}")
    public Users updateUser(@PathVariable UUID id, @RequestBody Users updates) {
        return userService.updateUser(id, updates);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }
}
