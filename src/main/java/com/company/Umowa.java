package com.company;

import javax.persistence.*;

@Entity
@Table(name="umowa")
public class Umowa {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idOferty")
    private Oferta ofertaDoKtorejPowstalaUmowa;

    @Column
    @Id
    private String idUmowy;

    @Column
    private String zawartoscUmowy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idKlienta")
    private Klient klientZawierajacyUmowe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idUzytkownika")
    private Uzytkownik uzytkownikWydajacyUmowe;

    @Column(name="czyPodpisana")
    private Boolean podpisana;

    public Umowa(Oferta ofertaDoKtorejPowstalaUmowa, String idUmowy, String zawartoscUmowy,
                 Klient klientZawierajacyUmowe, Uzytkownik uzytkownikWydajacyUmowe, Boolean podpisana) {
        this.ofertaDoKtorejPowstalaUmowa = ofertaDoKtorejPowstalaUmowa;
        this.idUmowy = idUmowy;
        this.zawartoscUmowy = zawartoscUmowy;
        this.klientZawierajacyUmowe = klientZawierajacyUmowe;
        this.uzytkownikWydajacyUmowe = uzytkownikWydajacyUmowe;
        this.podpisana = podpisana;
    }

    @Override
    public boolean equals(Object object){
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        Umowa doPorownania = (Umowa) object;
        if(doPorownania.getOfertaDoKtorejPowstalaUmowa().equals(this.ofertaDoKtorejPowstalaUmowa) &&
        doPorownania.getPodpisana() == this.getPodpisana() &&
        doPorownania.getIdUmowy().equals(this.getIdUmowy()) &&
        doPorownania.getKlientZawierajacyUmowe().equals(this.getKlientZawierajacyUmowe()) &&
        doPorownania.getUzytkownikWydajacyUmowe().equals(this.getUzytkownikWydajacyUmowe()) &&
        doPorownania.getZawartoscUmowy().equals(this.getZawartoscUmowy())) return true;
        return false;

    }

    public String getIdUmowy() {
        return idUmowy;
    }

    public void setIdUmowy(String idUmowy) {
        this.idUmowy = idUmowy;
    }

    public String getZawartoscUmowy() {
        return zawartoscUmowy;
    }

    public void setZawartoscUmowy(String zawartoscUmowy) {
        this.zawartoscUmowy = zawartoscUmowy;
    }

    public Klient getKlientZawierajacyUmowe() {
        return klientZawierajacyUmowe;
    }

    public void setKlientZawierajacyUmowe(Klient klientZawierajacyUmowe) {
        this.klientZawierajacyUmowe = klientZawierajacyUmowe;
    }

    public Uzytkownik getUzytkownikWydajacyUmowe() {
        return uzytkownikWydajacyUmowe;
    }

    public void setUzytkownikWydajacyUmowe(Uzytkownik uzytkownikWydajacyUmowe) {
        this.uzytkownikWydajacyUmowe = uzytkownikWydajacyUmowe;
    }

    public Oferta getOfertaDoKtorejPowstalaUmowa() {
        return ofertaDoKtorejPowstalaUmowa;
    }

    public void setOfertaDoKtorejPowstalaUmowa(Oferta ofertaDoKtorejPowstalaUmowa) {
        this.ofertaDoKtorejPowstalaUmowa = ofertaDoKtorejPowstalaUmowa;
    }

    public Boolean getPodpisana() {
        return podpisana;
    }

    public void setPodpisana(Boolean podpisana) {
        this.podpisana = podpisana;
    }
}