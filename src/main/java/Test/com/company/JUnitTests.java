package Test.com.company;

import com.company.*;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;

public class JUnitTests {
    @After
    public void tearDown(){
        KlienciSingleton.getKlienciSingletonInstance().deleteAll();
        OfertySingleton.getOfertySingletonInstance().deleteAll();
        RezerwacjeSingleton.getRezerwacjaSingletonInstance().deleteAll();
        UmowySingleton.getUmowySingletonInstance().deleteAll();
        UzytkownicySingleton.getUzytkownicySingletonInstance().deleteAll();
        AdminiSingleton.getAdminiSingletonInstance().deleteAll();
    }

    public static Admin inicjalizacjaAdmina() throws Exception {
        return new Admin("","","Adam", "Kowalski", "01234567891", "123456789",
                "adamkowalski@gmail.com");
    }

    public static Klient inicjalizacjaKlienta() throws Exception{
        return new Klient("", "", "Tomasz","Nowak", "01234567892", "456456456",
                "tomaszNowak@gmail.com");
    }

    public static Uzytkownik inicjalizacjaUzytkownika() throws Exception{
        return new Uzytkownik("", "", "Rafał","Kowalski", "12345678907", "567567567",
                "rafalKowalski@gmail.com");
    }

    // TESTY DO WYRAŻENIA REGULARNEGO ZASTOSOWANEGO W TELEFONIE

    @org.junit.Test(expected = Exception.class)
    public void czyPodanieZlegoNumeruTelefonuPowodujeBlad() throws Exception{
        Admin admin = inicjalizacjaAdmina();
        admin.setNumerTelefonu("Numer który nie ma sensu");
    }

    @org.junit.Test(expected = Exception.class)
    public void czyPodanieZaDługiegoNumeruTelefonuPowodujeBlad() throws Exception{
        Admin admin = inicjalizacjaAdmina();
        admin.setNumerTelefonu("1234567890");
    }

    @org.junit.Test(expected = Exception.class)
    public void czyPodanieZaKrotkiegoNumeruTelefonuPowodujeBlad() throws Exception{
        Admin admin = inicjalizacjaAdmina();
        admin.setNumerTelefonu("123");
    }

    @Test
    public void czyPodanieOdpowiedniegoNumeruTelefonuZmieniaGo() throws Exception{
        Admin admin = inicjalizacjaAdmina();
        admin.setNumerTelefonu("234234234");
        assertEquals("234234234", admin.getNumerTelefonu());
    }

    // TESTY ZASTOSOWANE DO WYRAŻENIA REGULARNEGO ZASTOSOWANEGO W MAILU

    @org.junit.Test(expected = Exception.class)
    public void czyPodanieZlegoMailaPowodujeBlad() throws Exception{
        Admin admin = inicjalizacjaAdmina();
        admin.setEmail("Mail który nie ma sensu");
    }

    @org.junit.Test(expected = Exception.class)
    public void czyPodanieMailaBezMałpyPowodujeBlad() throws Exception{
        Admin admin = inicjalizacjaAdmina();
        admin.setEmail("adamKowalski#gmail.com");
    }

    @org.junit.Test(expected = Exception.class)
    public void czyPodanieMailaBezPoczatkuPowodujeBlad() throws Exception{
        Admin admin = inicjalizacjaAdmina();
        admin.setEmail("@gmail.com");
    }

    @Test
    public void czyPodanieOdpowiedniegoMailaZmieniaGo() throws Exception{
        Admin admin = inicjalizacjaAdmina();
        admin.setEmail("adamPoczta23@gmail.com");
        assertEquals("adamPoczta23@gmail.com", admin.getEmail());
    }

    // TESTY SINGLETONOW

    @Test
    public void czyDodaniePrzezAdminaUmowyDodajeJaDoSingletona() throws Exception{
        Admin admin = inicjalizacjaAdmina();
        Klient klient = inicjalizacjaKlienta();
        Uzytkownik uzytkownik = inicjalizacjaUzytkownika();

        Oferta ofertaZKtorejPowstanieUmowa = new Oferta(0,"Tresc oferty","",0);
        Umowa umowa = new Umowa(ofertaZKtorejPowstanieUmowa, 0, "Tresc Umowy",
                klient);
        admin.stworzUmowe(umowa);

        assertEquals(umowa,UmowySingleton.getUmowySingletonInstance().getListaUmow().get(0));
    }

    @Test
    public void czyStworzenieOsobyByleJakiegoRodzajuSprawiaZeZostajeOnaDodanaDoOdpowiedniegoSingletona() throws Exception{
        Admin admin = inicjalizacjaAdmina();
        Klient klient = inicjalizacjaKlienta();
        Uzytkownik uzytkownik = inicjalizacjaUzytkownika();
        assertEquals(admin, AdminiSingleton.getAdminiSingletonInstance().getListaAdminow().get(0));
        assertEquals(klient, KlienciSingleton.getKlienciSingletonInstance().getListaKlientow().get(0));
        assertEquals(uzytkownik, UzytkownicySingleton.getUzytkownicySingletonInstance().getListaUzytkownikow().get(0));
    }

    @Test
    public void czyPoUsunieciuPrzezAdminaOfertyZnikaOnaZSingletona() throws Exception{
        Admin admin = inicjalizacjaAdmina();
        Oferta ofertaZKtorejPowstanieUmowa = new Oferta(0,"Tresc oferty", "",0);
        admin.stworzOferte(ofertaZKtorejPowstanieUmowa);
        assertEquals(ofertaZKtorejPowstanieUmowa, OfertySingleton.getOfertySingletonInstance().getListaOfert().get(0));
        admin.usunOferte(ofertaZKtorejPowstanieUmowa);
        assertTrue(OfertySingleton.getOfertySingletonInstance().getListaOfert().isEmpty());
    }

    @Test
    public void czyPoEdycjiOfertyPrzezAdminaJestOnaZedytowana() throws Exception {
        Admin admin = inicjalizacjaAdmina();
        Oferta ofertaZKtorejPowstanieUmowa = new Oferta(0,"Tresc oferty","",0);
        admin.stworzOferte(ofertaZKtorejPowstanieUmowa);
        assertEquals(ofertaZKtorejPowstanieUmowa.getZawartoscOferty(), "Tresc oferty");
        admin.edytujOferte(ofertaZKtorejPowstanieUmowa, "Nowa tresc oferty");
        assertEquals(ofertaZKtorejPowstanieUmowa.getZawartoscOferty(), "Nowa tresc oferty");
    }

    @Test
    public void czyPoStworzeniuPrzezAdminaOfertyJestOnaDodanaDoSingletona() throws Exception {
        Admin admin = inicjalizacjaAdmina();
        Oferta ofertaZKtorejPowstanieUmowa = new Oferta(0,"Tresc oferty","",0);
        admin.stworzOferte(ofertaZKtorejPowstanieUmowa);
        assertEquals(OfertySingleton.getOfertySingletonInstance().getListaOfert().get(0),ofertaZKtorejPowstanieUmowa);
    }

    @Test
    public void czyPoZarezerwowaniuOfertyPrzezKlientaJestOnaZarezerwowana() throws Exception {
        Admin admin = inicjalizacjaAdmina();
        Klient klient = inicjalizacjaKlienta();
        Oferta ofertaZKtorejPowstanieUmowa = new Oferta(0,"Tresc oferty","",0);
        admin.stworzOferte(ofertaZKtorejPowstanieUmowa);
        klient.zarezerwuj(ofertaZKtorejPowstanieUmowa, 0);
        assertEquals(RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji().get(0).getOfertaKtoraJestZarezerwowana(),
                ofertaZKtorejPowstanieUmowa);
    }

    @Test
    public void czyPoOdrezerwowaniuOfertyPrzezKlientaJestOnaOdrezerwowana() throws Exception {
        Admin admin = inicjalizacjaAdmina();
        Klient klient = inicjalizacjaKlienta();
        Oferta ofertaZKtorejPowstanieUmowa = new Oferta(0,"Tresc oferty","",0);
        admin.stworzOferte(ofertaZKtorejPowstanieUmowa);
        klient.zarezerwuj(ofertaZKtorejPowstanieUmowa, 0);
        klient.odrezerwuj(RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji().get(0));
        assertTrue(RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji().isEmpty());
    }
    @Test
    public void czyKlientPotrafiOdpowiednioZwrocicSwojeRezerwacjeZaPomocGetRezerwacjeKlienta() throws Exception {
        Admin admin = inicjalizacjaAdmina();
        Klient klient = inicjalizacjaKlienta();
        Oferta oferta1 = new Oferta(0,"Tresc oferty","",0);
        Oferta oferta2 = new Oferta(0,"Tresc oferty","",0);
        admin.stworzOferte(oferta1);
        admin.stworzOferte(oferta2);
        klient.zarezerwuj(oferta1, 0);
        klient.zarezerwuj(oferta2, 1);
        assertEquals(klient.getRezerwacjeKlienta().size(),2);
        assertEquals(klient.getRezerwacjeKlienta().get(0), RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji().get(0));
        assertEquals(klient.getRezerwacjeKlienta().get(1), RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji().get(1));
    }

    @Test
    public void czyPoUsunieciuOfertyUsuwaneSaTakzeRezerwacjeZNiaZwiazane() throws Exception {
        Admin admin = inicjalizacjaAdmina();
        Klient klient = inicjalizacjaKlienta();
        Oferta oferta1 = new Oferta(0,"Tresc oferty","",0);
        admin.stworzOferte(oferta1);
        klient.zarezerwuj(oferta1, 0);
        admin.usunOferte(oferta1);
        assertTrue(RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji().isEmpty());
    }
}