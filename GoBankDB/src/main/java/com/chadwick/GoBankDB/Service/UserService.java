package com.chadwick.GoBankDB.Service;

import com.chadwick.GoBankDB.Entity.Users;
import com.chadwick.GoBankDB.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Iterable<Users> getUsers(){
        return userRepository.findAll();
    }

    public Users getUserByID(String email){
        return userRepository.findById(email).get();
    }

    public Users createUser(Users user){
        return userRepository.save(user);
    }

    public Users updateUser(Users user){
        return userRepository.save(user);
    }

    public HttpStatus deleteUser(String email){
        userRepository.deleteById(email);
        return HttpStatus.OK;
    }
}
