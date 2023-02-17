package com.chadwick.GoBankDB.entity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name = "Transaction")
@Data
public class Transaction {
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant transactionDate;
    @Column(nullable = false, updatable = false)
    private String amount;
    @Column(nullable = false, updatable = false)
    private String type; // Credit or Debit
    @Column
    private String category; // Utilities, Food & Drink, Transportation, etc.
    @Column(nullable = false)
    private UUID associatedAccountId;
}
