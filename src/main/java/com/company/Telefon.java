package com.company;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Telefon {
    @Column
    private String numerTelefonu;

    public Telefon(String numerTelefonu) throws Exception {
        setTelefon(numerTelefonu);
    }

    public String getTelefon() {
        return numerTelefonu;
    }

    public void setTelefon(String numerTelefonu) throws Exception {
        if(java.util.regex.Pattern.matches("[0-9]{9}", numerTelefonu))
            this.numerTelefonu = numerTelefonu;
        else throw new Exception("Podano zły numer telefonu!");
    }
}
