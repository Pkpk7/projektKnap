package com.company;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name="klient")
public class Klient extends Osoba {
    private OfertySingleton wszystkieOferty = OfertySingleton.getOfertySingletonInstance();
    private RezerwacjeSingleton wszystkieRezerwacje = RezerwacjeSingleton.getRezerwacjaSingletonInstance();


    public Klient(String imie, String nazwisko, String pesel, String numerTelefonu, String email) throws Exception {
        super(imie, nazwisko, pesel, numerTelefonu, email);
        KlienciSingleton.getKlienciSingletonInstance().getListaKlientow().add(this);
    }

    /**
     * Tworzy nową rezerwacje dla danego klienta i oferty, a następnie
     * dodaje ją do globalnej listy rezerwacji.
     * @param oferta Oferta która dany klient będzie rezerwował
     */
    public void zarezerwuj(Oferta oferta){
        if(wszystkieOferty.getListaOfert().contains(oferta)){
            Rezerwacja rezerwacja = new Rezerwacja(oferta,this, UUID.randomUUID().toString());
            wszystkieRezerwacje.getListaRezerwacji().add(rezerwacja);
        }
    }

    /**
     * Usuwa daną rezerwacje z globalnej listy rezerwacji
     * @param rezerwacja Rezerwacja która chcemy usunąć
     */
    public void odrezerwuj(Rezerwacja rezerwacja){
        if(wszystkieRezerwacje.getListaRezerwacji().contains(rezerwacja)){
            wszystkieRezerwacje.getListaRezerwacji().remove(rezerwacja);
        }
    }

    public ArrayList<Rezerwacja> getRezerwacjeKlienta(){
        ArrayList<Rezerwacja> listaRezerwacjiKlienta = new ArrayList<Rezerwacja>();
        for(Rezerwacja rezerwacja : wszystkieRezerwacje.getListaRezerwacji()){
            if(rezerwacja.getKlientKtoryRezerwuje() == this){
                listaRezerwacjiKlienta.add(rezerwacja);
            }
        }
        return  listaRezerwacjiKlienta;
    }
}
