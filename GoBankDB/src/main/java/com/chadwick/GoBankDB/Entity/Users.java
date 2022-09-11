package com.chadwick.GoBankDB.Entity;
import com.chadwick.GoBankDB.Model.Address;
import com.chadwick.GoBankDB.Model.Birthdate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
public class Users {
    @Id
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column
    private String middleName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private int socialSecurity;
    @Column
    private String gender;
    @Embedded
    @Column(nullable = false)
    private Address address;
    @Embedded
    @Column(nullable = false)
    private Birthdate birthdate;
    @Column
    private double yearlyIncome;
    @Column
    private double monthlyIncome;
    @Column
    private double personalDebt;
    @Column
    private short ficoScore;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate joinDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Account> accounts;


}