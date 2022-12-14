package com.chadwick.GoBankDB.Entity;
import com.chadwick.GoBankDB.Model.Address;
import com.chadwick.GoBankDB.Model.Birthdate;
import com.chadwick.GoBankDB.Model.Recipient;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Column(nullable = false)
    private String firstName;
    @Column
    private String middleName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String socialSecurity;
    @Column
    private String gender;
    @Embedded
    @Column(nullable = false)
    private Address address;
    @Embedded
    @Column(nullable = false)
    private Birthdate birthdate;
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Account> accounts;


    public Users(String email, String password, String firstName, String middleName, String lastName, String socialSecurity, String gender, Address address, Birthdate birthdate, String yearlyIncome, String monthlyIncome, String personalDebt, String ficoScore, LocalDate joinDate, List<Recipient> recipientList, List<Account> accounts) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.socialSecurity = socialSecurity;
        this.gender = gender;
        this.address = address;
        this.birthdate = birthdate;
        this.yearlyIncome = yearlyIncome;
        this.monthlyIncome = monthlyIncome;
        this.personalDebt = personalDebt;
        this.ficoScore = ficoScore;
        this.joinDate = joinDate;
        this.recipientList = recipientList;
        this.accounts = accounts;
    }

    public Users() {
    }
}
