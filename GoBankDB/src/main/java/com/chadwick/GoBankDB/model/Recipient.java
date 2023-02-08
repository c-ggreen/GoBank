package com.chadwick.GoBankDB.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.UUID;


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
    private UUID recipientAccountId;
}
