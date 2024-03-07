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
    public void setSiren(String siren) {this.siren = siren;}


    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}


    public String getRue() {return rue;}
    public void setRue(String rue) {this.rue = rue;}


    public String getVille() {return ville;}
    public void setVille(String ville) {this.ville = ville;}


    public String getCode_postal() {return code_postal;}
    public void setCode_postal(String code_postal) {this.code_postal = code_postal;}


    public String getDescriptif() {return descriptif;}
    public void setDescriptif(String descriptif) {this.descriptif = descriptif;}

    @Override
    public String toString() {
        return "Boulangerie{" +
                "siren='" + siren + '\'' +
                ", nom='" + nom + '\'' +
                ", rue='" + rue + '\'' +
                ", ville='" + ville + '\'' +
                ", code_postal='" + code_postal + '\'' +
                ", descriptif='" + descriptif + '\'' +
                '}';
    }
}

