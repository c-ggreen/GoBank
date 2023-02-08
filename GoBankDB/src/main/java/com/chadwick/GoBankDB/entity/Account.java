package com.chadwick.GoBankDB.entity;
import com.chadwick.GoBankDB.model.Name;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "Account")
@Data
public class Account {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID accountId;
    @Column(nullable = false)
    private UUID accountOwnerId;
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
    private String type; // Checking, Savings, Investment, Credit
    @Column(nullable = false)
    private String balance;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant creationDate;
    @Column
    @ElementCollection
    private List<UUID> transactionIDs; // only storing the transaction id's instead of the full transactions
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "account_id")
//    private List<Transaction> transactions;
}
