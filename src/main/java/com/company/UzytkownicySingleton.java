package com.company;

import java.util.ArrayList;

public class UzytkownicySingleton {
    private ArrayList<Uzytkownik> listaUzytkownikow = new ArrayList<Uzytkownik>();
    private static UzytkownicySingleton uzytkownicySingleton = null;
    private UzytkownicySingleton() {};

    public static UzytkownicySingleton getUzytkownicySingletonInstance(){
        if(uzytkownicySingleton==null)
            uzytkownicySingleton = new UzytkownicySingleton();
        return uzytkownicySingleton;
    }


    public ArrayList<Uzytkownik> getListaUzytkownikow() {
        return listaUzytkownikow;
    }

    /**
     * Usuwa wszystkich uzytkownikow z listy
     */
    public void deleteAll(){
        this.listaUzytkownikow = new ArrayList<Uzytkownik>();
    }
}
