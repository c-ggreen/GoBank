package com.chadwick.GoBankDB.repository;

import com.chadwick.GoBankDB.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Query("SELECT x FROM Account x WHERE x.accountOwnerId = :accountOwnerId")
    List<Account> findAccountsByOwnerId(
            @Param("accountOwnerId") int accountOwnerId
    );
}
