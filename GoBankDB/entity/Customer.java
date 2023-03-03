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
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "Customer")
@Data
//@NoArgsConstructor
public class Customer {
    @Id
//    @GeneratedValue
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


    public Customer(
            String email,
            String password,
            Name name,
            String socialSecurity,
            String gender,
            Address address,
            Birthday birthday,
            String yearlyIncome,
            String monthlyIncome,
            String personalDebt,
            String ficoScore,
            Instant joinDate,
            List<Recipient> recipientList,
            List<Long> accountIDs
//            List<Account> accounts
    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.socialSecurity = socialSecurity;
        this.gender = gender;
        this.address = address;
        this.birthday = birthday;
        this.yearlyIncome = yearlyIncome;
        this.monthlyIncome = monthlyIncome;
        this.personalDebt = personalDebt;
        this.ficoScore = ficoScore;
        this.joinDate = joinDate;
        this.recipientList = recipientList;
        this.accountIDs = accountIDs;
//        this.accounts = accounts;
    }

    public Customer() {
    }

    public int randomInt(){
        int min = 1_000_000;
        int max = 9_999_999;
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
