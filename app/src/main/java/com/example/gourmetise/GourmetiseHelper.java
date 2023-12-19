package com.example.gourmetise;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GourmetiseHelper extends SQLiteOpenHelper {
    public GourmetiseHelper(@Nullable Context context)
    {
        super(context, "baseGourmetise.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// création des tables de la base embarquée
// création de la table
        db.execSQL("CREATE TABLE Boulangerie ("
                + "siren TEXT NOT NULL PRIMARY KEY,"
                + "nom TEXT NOT NULL,"
                + "rue TEXT NOT NULL,"
                + "ville TEXT NOT NULL,"
                + "code_postal TEXT NOT NULL,"
                + "descriptif TEXT NOT NULL);");


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Boulangerie;");
        onCreate(db);
    }
}

