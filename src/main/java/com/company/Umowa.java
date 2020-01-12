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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idUmowy;

    @Column
    private String zawartoscUmowy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idKlienta")
    private Klient klientZawierajacyUmowe;




    public Umowa(Oferta ofertaDoKtorejPowstalaUmowa, int idUmowy, String zawartoscUmowy,
                 Klient klientZawierajacyUmowe) {
        this.ofertaDoKtorejPowstalaUmowa = ofertaDoKtorejPowstalaUmowa;
        this.idUmowy = idUmowy;
        this.zawartoscUmowy = zawartoscUmowy;
        this.klientZawierajacyUmowe = klientZawierajacyUmowe;
    }

    public Umowa(){}

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
        doPorownania.getIdUmowy() == this.getIdUmowy() &&
        doPorownania.getKlientZawierajacyUmowe().equals(this.getKlientZawierajacyUmowe()) &&
        doPorownania.getZawartoscUmowy().equals(this.getZawartoscUmowy())) return true;
        return false;

    }

    public int getIdUmowy() {
        return idUmowy;
    }

    public void setIdUmowy(int idUmowy) {
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



    public Oferta getOfertaDoKtorejPowstalaUmowa() {
        return ofertaDoKtorejPowstalaUmowa;
    }

    public void setOfertaDoKtorejPowstalaUmowa(Oferta ofertaDoKtorejPowstalaUmowa) {
        this.ofertaDoKtorejPowstalaUmowa = ofertaDoKtorejPowstalaUmowa;
    }


}
