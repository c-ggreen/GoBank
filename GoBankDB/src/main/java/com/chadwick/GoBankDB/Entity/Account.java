package com.chadwick.GoBankDB.Entity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
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
    private String defaultAccountName;
    @Column(nullable = false)
    private String accountNickName;
    @Column(nullable = false)
    private String type; // Checking, Savings, Investment, Credit
    @Column(nullable = false)
    private double balance;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate creationDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<Transaction> transactions;
}
