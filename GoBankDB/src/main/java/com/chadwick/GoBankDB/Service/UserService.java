package com.chadwick.GoBankDB.Service;

import com.chadwick.GoBankDB.Entity.User;
import com.chadwick.GoBankDB.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserByID(String email){
        return userRepository.findById(email).get();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public HttpStatus deleteUser(String email){
        userRepository.deleteById(email);
        return HttpStatus.OK;
    }
}
