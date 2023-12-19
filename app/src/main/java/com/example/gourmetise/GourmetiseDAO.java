package com.example.gourmetise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GourmetiseDAO<Boulangerie> {
    private SQLiteDatabase maBase;
    private GourmetiseHelper monHelper;
    public GourmetiseDAO(Context context){
        monHelper = new GourmetiseHelper(context);
        maBase = monHelper.getWritableDatabase();
    }

    public Cursor LesBoulangeries() {
        Cursor curseurContact = maBase.rawQuery("SELECT siren, nom, rue, ville, code_postal,descriptif from Boulangerie " , new String[] {});
        return curseurContact;
    }


    public void ajouterBoulangerie(com.example.gourmetise.Boulangerie uneBoulangerie) {
        //création d'un ContentValues
        ContentValues v = new ContentValues();
        // ajout des propriétés au ContentValues
        v.put("siren", uneBoulangerie.getSiren());
        v.put("nom", uneBoulangerie.getNom());
        v.put("rue", uneBoulangerie.getRue());
        v.put("ville", uneBoulangerie.getVille());
        v.put("code_postal", uneBoulangerie.getCode_postal());
        v.put("descriptif", uneBoulangerie.getDescriptif());


    }

    public void supprimerTous() {
    }

}

