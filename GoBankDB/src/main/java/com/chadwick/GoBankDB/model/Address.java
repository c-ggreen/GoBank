package com.chadwick.GoBankDB.model;

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
    @Column
    private String unit;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String country;
}
