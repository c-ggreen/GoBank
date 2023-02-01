package com.chadwick.GoBankDB.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;


@Embeddable
@Data
@NoArgsConstructor
public class Recipient {
    @Column(nullable = false)
    private String recipientEmailId;
    @Embedded
    @Column(nullable = false)
    private Name recipientName;
    @Column(nullable = false)
    private String recipientAccountId;
}
