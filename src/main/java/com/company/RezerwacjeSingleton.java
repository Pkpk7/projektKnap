package com.company;

import java.util.ArrayList;

public class RezerwacjeSingleton {

    private static RezerwacjeSingleton rezerwacjeSingleton = null;
    private ArrayList<Rezerwacja> listaRezerwacji = new ArrayList<Rezerwacja>();
    private RezerwacjeSingleton(){};

    public ArrayList<Rezerwacja> getListaRezerwacji() {
        return listaRezerwacji;
    }

    public static RezerwacjeSingleton getRezerwacjaSingletonInstance(){
        if(rezerwacjeSingleton == null)
            rezerwacjeSingleton = new RezerwacjeSingleton();
        return rezerwacjeSingleton;
    }

    /**
     * Usuwa wszystkie rezerwacje.
     */
    public void deleteAll(){
        this.listaRezerwacji = new ArrayList<Rezerwacja>();
    }
}
