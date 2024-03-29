package com.chadwick.GoBankDB.entity;
import com.chadwick.GoBankDB.enums.AccountStatus;
import com.chadwick.GoBankDB.enums.AccountType;
import com.chadwick.GoBankDB.model.Name;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "Account")
@Data
public class Account {
    @Id
    @Column(nullable = false)
    private long accountId = randomLong();
    @Column(nullable = false)
    private int accountOwnerId;
    @Embedded
    @Column(nullable = false)
    private Name accountOwnerName; // Should be sourced from Users name fields
    @Column(nullable = false)
    private String accountOwnerEmail;
    @Column(nullable = false)
    private String defaultAccountName;
    @Column(nullable = false)
    private String accountNickName;
    @Column(nullable = false)
    private AccountStatus accountStatus; // OPEN, HOLD, CLOSED
    @Column(nullable = false, updatable = false)
    private AccountType type; // CHECKING, SAVINGS, INVESTMENT, CREDIT
    @Column(nullable = false)
    private String balance;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant creationDate;
    @Column
    @ElementCollection
    private List<Long> transactionIDs; // only storing the transaction id's instead of the full transactions
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "account_id")
//    private List<Transaction> transactions;

    public long randomLong(){
        long leftLimit = 1_000_000_000L;
        long rightLimit = 9_999_999_999L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}