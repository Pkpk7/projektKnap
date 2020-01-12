package com.company;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Email {
    @Column
    private String email;

    public Email(String email) throws Exception {
        setEmail(email);
    }

    public Email(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception{
        if(java.util.regex.Pattern.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", email))
            this.email = email;
        else throw new Exception("Podano zły mail!");
    }
}
