package com.company;

import javax.persistence.*;

@MappedSuperclass
public class Osoba {
    @Column
    private String imie;

    @Column
    private String nazwisko;

    @Column
    @Id
    private String pesel;

    @Column
    @Embedded
    private Telefon numerTelefonu;

    @Column
    @Embedded
    private Email email;

    public Osoba(String imie, String nazwisko, String pesel, String numerTelefonu, String email) throws Exception {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.numerTelefonu = new Telefon(numerTelefonu);
        this.email = new Email(email);
    }

    public Osoba(){}

    // GETERY I SETERY

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNumerTelefonu() {
        return numerTelefonu.getTelefon();
    }

    public void setNumerTelefonu(String numerTelefonu) throws Exception {
        this.numerTelefonu.setTelefon(numerTelefonu);
    }

    public String getEmail() {
        return email.getEmail();
    }

    public void setEmail(String email) throws Exception{
        this.email.setEmail(email);
    }

    @Override
    public boolean equals(Object object){
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        Osoba doPorownania = (Osoba) object;
        if(doPorownania.getEmail().equals(this.getEmail()) &&
        doPorownania.getImie().equals(this.getImie()) &&
        doPorownania.getNazwisko().equals(this.getNazwisko()) &&
        doPorownania.getNumerTelefonu().equals(this.getNumerTelefonu())) return true;
        return false;

    }
}
