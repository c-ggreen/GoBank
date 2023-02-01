package com.chadwick.GoBankDB.repository;

import com.chadwick.GoBankDB.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends CrudRepository<Users, UUID> {
    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Users findUserByEmail(
            @Param("email") String email
    );
}
