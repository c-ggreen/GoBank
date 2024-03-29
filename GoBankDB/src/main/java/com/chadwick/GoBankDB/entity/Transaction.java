package com.chadwick.GoBankDB.entity;
import com.chadwick.GoBankDB.enums.TransactionCategory;
import com.chadwick.GoBankDB.enums.TransactionType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.Instant;


@Entity
@Table(name = "Transaction")
@Data
public class Transaction {
    @Id
    @Column(nullable = false)
    private long id = randomLong();
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant transactionDate;
    @Column(nullable = false, updatable = false)
    private String amount;
    @Column(nullable = false, updatable = false)
    private TransactionType type; // CREDIT or DEBIT
    @Column(nullable = false)
    private TransactionCategory category; // Utilities, Food & Drink, Transportation, etc.
    @Column(nullable = false)
    private long associatedAccountId;

    public long randomLong(){
        long leftLimit = 1_000_000_000_000_000L; // quadrillion
        long rightLimit = 9_999_999_999_999_999L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}