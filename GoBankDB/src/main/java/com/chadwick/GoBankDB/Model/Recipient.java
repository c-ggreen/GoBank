package com.chadwick.GoBankDB.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
@Data
@NoArgsConstructor
public class Recipient {
    @Column(nullable = false)
    private String recipientEmailId;
    @Column(nullable = false)
    private String recipientName;
    @Column(nullable = false)
    private String recipientAccountId;
}
