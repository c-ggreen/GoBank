package com.chadwick.GoBankDB.repository;

import com.chadwick.GoBankDB.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, UUID> {
}
