package com.chadwick.GoBankDB.Repository;

import com.chadwick.GoBankDB.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
