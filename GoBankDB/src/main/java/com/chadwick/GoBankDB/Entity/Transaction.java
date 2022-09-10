package com.chadwick.GoBankDB.Entity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "Transaction")
@Data
public class Transaction {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime transactionDate;
    @Column(nullable = false)
    private long amount;
    @Column(nullable = false)
    private String type; // Credit or Debit
}
