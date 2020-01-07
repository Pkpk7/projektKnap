package com.company;

import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="uzytkownik")
public class Uzytkownik extends Osoba {
    private KlienciSingleton wszyscyKlienci = KlienciSingleton.getKlienciSingletonInstance();
    private OfertySingleton wszystkieOferty = OfertySingleton.getOfertySingletonInstance();
    private UmowySingleton wszystkieUmowy = UmowySingleton.getUmowySingletonInstance();
    private RezerwacjeSingleton wszystkieRezerwacje = RezerwacjeSingleton.getRezerwacjaSingletonInstance();


    public Uzytkownik(String imie, String nazwisko, String pesel, String numerTelefonu,
                      String email) throws Exception {
        super(imie, nazwisko, pesel, numerTelefonu, email);
        UzytkownicySingleton.getUzytkownicySingletonInstance().getListaUzytkownikow().add(this);
    }

    public Uzytkownik(){}

    /**
     * Tworzy nowa umowe i dodaje ją do globalnej listy umów. Następnie sprawdza
     * według której oferty powstała umowa, a nastepnie usuwa daną oferte i wszystkie
     * rezerwacje do tej oferty.
     * @param umowa Umowa ktora bedzie podpisywana przez klienta
     * @param klient Klient ktory podpisywał bedzie umowe
     */
    public void podpisanieUmowy(Umowa umowa, Klient klient){
        if(wszystkieUmowy.getListaUmow().contains(umowa) && wszyscyKlienci.getListaKlientow().contains(klient)){
            umowa.setPodpisana(true);
            wszystkieOferty.getListaOfert().remove(umowa.getOfertaDoKtorejPowstalaUmowa());
            for(Rezerwacja rezerwacja : wszystkieRezerwacje.getListaRezerwacji()){
                if(rezerwacja.getOfertaKtoraJestZarezerwowana() == umowa.getOfertaDoKtorejPowstalaUmowa()) {
                    wszystkieRezerwacje.getListaRezerwacji().remove(rezerwacja);
                }
            }

        }
    }
}
