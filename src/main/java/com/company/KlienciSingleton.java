package com.company;

import java.util.ArrayList;

public class KlienciSingleton {
    private static KlienciSingleton klienciSingleton = new KlienciSingleton();
    private ArrayList<Klient> listaKlientow = new ArrayList<Klient>();
    private KlienciSingleton(){};

    public static KlienciSingleton getKlienciSingletonInstance(){
        if(klienciSingleton == null)
            klienciSingleton = new KlienciSingleton();
        return klienciSingleton;
    }

    public ArrayList<Klient> getListaKlientow() {
        return listaKlientow;
    }

    /**
     * Usuwa wszystkich klientow.
     */
    public void deleteAll(){
        this.listaKlientow = new ArrayList<Klient>();
    }

}
