package com.payment.exceptions;

public class UsersException extends RuntimeException{
    public UsersException() {

    }
    public UsersException(String msg) {
            super(msg);
    }
}
