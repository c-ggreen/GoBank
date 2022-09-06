package com.chadwick.GoBankDB.Controller;

import com.chadwick.GoBankDB.Entity.User;
import com.chadwick.GoBankDB.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public Iterable<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{email}")
    public User getUserByID(@PathVariable String email){
        return userService.getUserByID(email);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
       return userService.createUser(user);
    }

    @PatchMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{email}")
    public HttpStatus deleteUser(@PathVariable String email){
        return userService.deleteUser(email);
    }
}
