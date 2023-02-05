package com.chadwick.GoBankDB.entity;

import com.chadwick.GoBankDB.model.Address;
import com.chadwick.GoBankDB.model.Birthday;
import com.chadwick.GoBankDB.model.Name;
import com.chadwick.GoBankDB.model.Recipient;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Users")
@Data
//@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID userId;
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
    private LocalDate joinDate;
    @Column
    @ElementCollection
    private List<Recipient> recipientList;

    @Column
    @ElementCollection
    private List<UUID> accountIDs;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private List<Account> accounts;


    public Users(
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
            LocalDate joinDate,
            List<Recipient> recipientList,
            List<UUID> accountIDs
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

    public Users() {
    }
}
