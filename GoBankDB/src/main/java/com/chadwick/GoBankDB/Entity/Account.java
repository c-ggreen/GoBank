package com.chadwick.GoBankDB.Entity;
import lombok.Data;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "Account")
@Data
public class Account {
    @Id
    @Column
    @GeneratedValue
    private UUID id;
    @Column
    private String name;
    @Column
    private long balance;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<Transaction> transactions;
}
