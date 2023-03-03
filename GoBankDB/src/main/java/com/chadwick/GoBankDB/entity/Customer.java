package com.chadwick.GoBankDB.entity;

import com.chadwick.GoBankDB.model.Address;
import com.chadwick.GoBankDB.model.Birthday;
import com.chadwick.GoBankDB.model.Name;
import com.chadwick.GoBankDB.model.Recipient;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "Customer")
@Data
public class Customer {
    @Id
    @Column(nullable = false)
    private int customerId = randomInt();
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Embedded
    @Column(nullable = false)
    private Name name;
    @Column(nullable = false)
    private String socialSecurity;
    @Column
    private String gender;
    @Embedded
    @Column(nullable = false)
    private Address address;
    @Embedded
    @Column(nullable = false)
    private Birthday birthday;
    @Column
    private String yearlyIncome;
    @Column
    private String monthlyIncome;
    @Column
    private String personalDebt;
    @Column
    private String ficoScore;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant joinDate;
    @Column
    @ElementCollection
    private List<Recipient> recipientList;

    @Column
    @ElementCollection
    private List<Long> accountIDs;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private List<Account> accounts;

    public int randomInt(){
        int min = 1_000_000;
        int max = 9_999_999;
        return min + (int) (Math.random() *(max - min));
    }
}