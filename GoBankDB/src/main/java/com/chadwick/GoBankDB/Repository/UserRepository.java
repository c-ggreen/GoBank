package com.chadwick.GoBankDB.Repository;

import com.chadwick.GoBankDB.Entity.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<Users, UUID> {
}
