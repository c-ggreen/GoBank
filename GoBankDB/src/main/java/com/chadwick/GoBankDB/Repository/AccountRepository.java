package com.chadwick.GoBankDB.Repository;

import com.chadwick.GoBankDB.Entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long > {
}
