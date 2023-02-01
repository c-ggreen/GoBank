package com.chadwick.GoBankDB.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class Birthdate {
    @Column(nullable = false)
    private String day;
    @Column(nullable = false)
    private String month;
    @Column(nullable = false)
    private String year;
}
