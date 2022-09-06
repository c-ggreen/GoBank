package com.chadwick.GoBankDB.Controller;

import com.chadwick.GoBankDB.Entity.Users;
import com.chadwick.GoBankDB.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public Iterable<Users> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{email}")
    public Users getUserByID(@PathVariable String email){
        return userService.getUserByID(email);
    }

    @PostMapping
    public Users createUser(@RequestBody Users user){
       return userService.createUser(user);
    }

    @PatchMapping
    public Users updateUser(@RequestBody Users user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{email}")
    public HttpStatus deleteUser(@PathVariable String email){
        return userService.deleteUser(email);
    }
}
