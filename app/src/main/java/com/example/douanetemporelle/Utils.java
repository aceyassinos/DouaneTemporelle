package com.example.douanetemporelle;

public class Utils {
    // akher 4 numbers mn id etudaint
    public static final String matricule ="1234";
    // keys l assasiyin
    public static final String nom = "NOM_VOYAGEUR";
    public static final String naissance = "ANNEE_NAISSANCE";
    public static final String destination = "DESTINATION";
    public static final String motif = "MOTIF";
    public static final String verdict = "VERDICT";
    //methode wle tari9a li bch n3mlou beha cles
    public static String cle(String matricule, String nomcle){
        return matricule +"_"+ nomcle;
    }
}