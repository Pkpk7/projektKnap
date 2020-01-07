package com.company;

import java.util.ArrayList;

public class UmowySingleton {
    private static UmowySingleton umowySingleton = null;
    private ArrayList<Umowa> listaUmow = new ArrayList<Umowa>();
    private UmowySingleton(){};
    public static UmowySingleton getUmowySingletonInstance(){
        if(umowySingleton==null)
            umowySingleton = new UmowySingleton();
        return umowySingleton;
    }
    public ArrayList<Umowa> getListaUmow() {
        return listaUmow;
    }

    /**
     * Usuwa wszystkie umowy.
     */
    public void deleteAll(){
        this.listaUmow = new ArrayList<Umowa>();
    }
}
