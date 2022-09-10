package com.chadwick.GoBankDB.Entity;
import com.chadwick.GoBankDB.Model.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private int socialSecurityNumber;
    @Column
    private String gender;
    @Column
    private Address address;
    @Column
    private String birthdate;
    @Column
    private String yearlyIncome;
    @Column
    private String monthlyIncome;
    @Column
    private String debt;
    @Column(nullable = false)
    private String ficoScore;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Account> accounts;


}
