package com.chadwick.GoBankDB.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class Name {
    @Column(nullable = false)
    private String first;
    @Column(nullable = false)
    private String middle;
    @Column(nullable = false)
    private String last;
}
