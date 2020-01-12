package com.company;

import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="uzytkownik")
public class Uzytkownik extends Osoba {
    transient private KlienciSingleton wszyscyKlienci = KlienciSingleton.getKlienciSingletonInstance();
    transient private OfertySingleton wszystkieOferty = OfertySingleton.getOfertySingletonInstance();
    transient private UmowySingleton wszystkieUmowy = UmowySingleton.getUmowySingletonInstance();
    transient private RezerwacjeSingleton wszystkieRezerwacje = RezerwacjeSingleton.getRezerwacjaSingletonInstance();


    public Uzytkownik(String login, String password, String imie, String nazwisko, String pesel, String numerTelefonu,
                      String email) throws Exception {
        super(login, password, imie, nazwisko, pesel, numerTelefonu, email);
        UzytkownicySingleton.getUzytkownicySingletonInstance().getListaUzytkownikow().add(this);
    }

    public Uzytkownik(){}



}
