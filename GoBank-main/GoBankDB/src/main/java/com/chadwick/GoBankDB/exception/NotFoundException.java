package com.chadwick.GoBankDB.exception;

//this is the class that I would recreate for each type of error ex.404,500, etc.
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
