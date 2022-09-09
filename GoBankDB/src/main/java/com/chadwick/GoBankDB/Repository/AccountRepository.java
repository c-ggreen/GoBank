package com.chadwick.GoBankDB.Repository;

import com.chadwick.GoBankDB.Entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, UUID> {
}
