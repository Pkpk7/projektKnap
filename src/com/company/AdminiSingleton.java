package com.company;

import java.util.ArrayList;

public class AdminiSingleton {
    private ArrayList<Admin> listaAdminow = new ArrayList<Admin>();
    private static AdminiSingleton adminiSingleton = null;
    private AdminiSingleton(){};

    public static AdminiSingleton getAdminiSingletonInstance(){
        if(adminiSingleton==null)
            adminiSingleton = new AdminiSingleton();
        return adminiSingleton;
    }

    public ArrayList<Admin> getListaAdminow() {
        return listaAdminow;
    }

    /**
     * Usuwa wszystkich adminow z listy.
     */
    public void deleteAll(){
        this.listaAdminow = new ArrayList<Admin>();
    }
}
