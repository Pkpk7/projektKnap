package com.company;

import java.util.ArrayList;

public class OfertySingleton {
    private ArrayList<Oferta> listaOfert = new ArrayList<Oferta>();
    private static OfertySingleton ofertySingleton = null;

    private OfertySingleton(){};
    public static OfertySingleton getOfertySingletonInstance(){
        if(ofertySingleton==null){
            ofertySingleton = new OfertySingleton();
        }
        return ofertySingleton;
    }
    public ArrayList<Oferta> getListaOfert() {
        return listaOfert;
    }

    /**
     * Usuwa wszystkie elementy z listy ofert.
     */
    public void deleteAll(){
        this.listaOfert = new ArrayList<Oferta>();
    }


}
