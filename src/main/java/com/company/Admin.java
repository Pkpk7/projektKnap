package com.company;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Iterator;

@Entity
@Table(name="admin")
public class Admin extends Osoba {

    private KlienciSingleton wszyscyKlienci = KlienciSingleton.getKlienciSingletonInstance();
    private OfertySingleton wszystkieOferty = OfertySingleton.getOfertySingletonInstance();
    private UmowySingleton wszystkieUmowy = UmowySingleton.getUmowySingletonInstance();
    private RezerwacjeSingleton wszystkieRezerwacje = RezerwacjeSingleton.getRezerwacjaSingletonInstance();

    public Admin(String imie, String nazwisko, String pesel,
                 String numerTelefonu, String email) throws Exception{

        super(imie, nazwisko, pesel, numerTelefonu, email);
        AdminiSingleton.getAdminiSingletonInstance().getListaAdminow().add(this);
    }

    public Admin(){}

    /**
     * Jeżeli dana oferta nie występuje w globalnej liscie ofert
     * dodaj ją ona do niej.
     * @param oferta Oferta do dodania do globalnej listy ofert
     */
    public void stworzOferte(Oferta oferta){
        if(!wszystkieOferty.getListaOfert().contains(oferta)){
            wszystkieOferty.getListaOfert().add(oferta);
        }
    }

    /**
     * Jeżeli dana umowa nie wystepuje w globalnej liscie umów
     * dodaj ją do niej.
     * @param umowa Umowa do stworzenia
     */
    public void stworzUmowe(Umowa umowa){
        if(!wszystkieUmowy.getListaUmow().contains(umowa)){
            wszystkieUmowy.getListaUmow().add(umowa);
        }
    }

    /**
     * Jeżeli dana oferta istnieje to podmien jej zawartość z nową podaną
     * @param oferta oferta do edycji
     * @param nowaZawartoscOferty tekst który zostanie podstawiony do podanej oferty
     */
    public void edytujOferte(Oferta oferta, String nowaZawartoscOferty){
        if(wszystkieOferty.getListaOfert().contains(oferta)){
            oferta.setZawartoscOferty(nowaZawartoscOferty);
        }
    }

    /**
     * Jeżeli oferta jest w liście globalnej ofert to usuń ją, a następnie
     * usuń wszystkie rezerwacje dla tej oferty.
     * @param oferta oferta do usunięcia
     */
    public void usunOferte(Oferta oferta){
        if(wszystkieOferty.getListaOfert().contains(oferta)){
            wszystkieOferty.getListaOfert().remove(oferta);
            for(Iterator<Rezerwacja> it = wszystkieRezerwacje.getListaRezerwacji().iterator();it.hasNext();){
                Rezerwacja rezerwacja = it.next();
                if(rezerwacja.getOfertaKtoraJestZarezerwowana() == oferta) {
                    it.remove();
                }
            }
        }
    }
}
