package com.company;

public class Oferta {
    private String idOferty;
    private String zawartoscOferty;

    public Oferta(String idOferty, String zawartoscOferty) {
        this.idOferty = idOferty;
        this.zawartoscOferty = zawartoscOferty;
    }

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
