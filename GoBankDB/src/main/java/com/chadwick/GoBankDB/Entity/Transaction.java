package com.chadwick.GoBankDB.Entity;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "Transaction")
@Data
public class Transaction {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column
    private long amount;
}
