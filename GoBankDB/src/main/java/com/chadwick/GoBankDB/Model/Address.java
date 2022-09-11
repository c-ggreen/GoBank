package com.chadwick.GoBankDB.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
@Data
@NoArgsConstructor
public class Address {
    @Column(nullable = false)
    private String street;
    private String unit;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private int zipCode;
    @Column(nullable = false)
    private String country;
}
