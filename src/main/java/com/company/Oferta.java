package com.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oferta")
public class Oferta {
    @Column
    @Id
    private String idOferty;

    @Column
    private String zawartoscOferty;

    public Oferta(String idOferty, String zawartoscOferty) {
        this.idOferty = idOferty;
        this.zawartoscOferty = zawartoscOferty;
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
        if(doPorownania.getIdOferty().equals(this.getIdOferty()) &&
        doPorownania.getZawartoscOferty().equals(this.getZawartoscOferty())) return true;
        return false;
    }

    public String getIdOferty() {
        return idOferty;
    }

    public void setIdOferty(String idOferty) {
        this.idOferty = idOferty;
    }

    public String getZawartoscOferty() {
        return zawartoscOferty;
    }

    public void setZawartoscOferty(String zawartoscOferty) {
        this.zawartoscOferty = zawartoscOferty;
    }
}
