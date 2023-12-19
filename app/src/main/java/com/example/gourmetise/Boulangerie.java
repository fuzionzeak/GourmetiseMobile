package com.example.gourmetise;

public class Boulangerie {

    private  String siren;
    private  String nom;
    private String  rue;
    private String ville;
    private String code_postal;
    private  String descriptif;




    public Boulangerie() {

        this.siren="";
        this.nom="";
        this.rue="";
        this.ville="";
        this.code_postal="";
        this.descriptif="";


    }

    public String getSiren() {return siren;}

    public String getNom() {return nom;}

    public String getRue() {return rue;}
    public String getVille() {return ville;}
    public String getCode_postal() {return code_postal;}
    public String getDescriptif() {return descriptif;}


}

