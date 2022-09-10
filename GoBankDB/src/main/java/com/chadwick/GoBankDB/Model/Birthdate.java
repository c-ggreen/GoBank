package com.chadwick.GoBankDB.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class Birthdate {
    private byte day;
    private String month;
    private short year;
}
