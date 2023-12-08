package com.example.gourmetise;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

public class GourmetiseDAO {
    private SQLiteDatabase maBase;
    private GourmetiseHelper monHelper;
    public GourmetiseDAO(Context context){
        monHelper = new GourmetiseHelper(context);
        maBase = monHelper.getWritableDatabase();
    }

}

