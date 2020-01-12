package com.company;

import javax.persistence.*;

@Entity
@Table(name="oferta")
public class Oferta {
    @Column
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idOferty;

    @Column
    private String adres;

    @Column
    private String zawartoscOferty;

    @Column
    private int cena;

    public Oferta(int idOferty, String zawartoscOferty, String adres, int cena) {
        this.idOferty = idOferty;
        this.zawartoscOferty = zawartoscOferty;
        this.adres = adres;
        this.cena = cena;
    }

    public Oferta(){}

    @Override
    public boolean equals(Object object){
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        Oferta doPorownania = (Oferta) object;
        if(doPorownania.getIdOferty()==this.getIdOferty() &&
        doPorownania.getZawartoscOferty().equals(this.getZawartoscOferty()) &&
        doPorownania.adres.equals(this.adres)) return true;
        return false;
    }

    public int getIdOferty() {
        return idOferty;
    }

    public void setIdOferty(int idOferty) {
        this.idOferty = idOferty;
    }

    public String getZawartoscOferty() {
        return zawartoscOferty;
    }

    public void setZawartoscOferty(String zawartoscOferty) {
        this.zawartoscOferty = zawartoscOferty;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}
