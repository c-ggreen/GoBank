package com.chadwick.GoBankDB.Repository;

import com.chadwick.GoBankDB.Entity.Users;
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
