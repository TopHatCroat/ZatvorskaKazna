package com.tophatcroat.zatvorskakazna.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonio on 26/08/15.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "zatvorskaKazna.db";
    public static final int DATABASE_VERSION = 2;

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(Database.lawTable.CREATE);
            db.execSQL(Database.lawTable.INSERT_LAWS);
            db.execSQL(Database.suggestionTable.CREATE);
            db.execSQL(Database.suggestionTable.INSERT_SUGGESTIONS);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Database.lawTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Database.suggestionTable.TABLE_NAME);
        onCreate(db);
    }

//    public List<LawsModel> read(String search){
////        List<lawsModel> recordsList = new ArrayList<lawsModel>();
////
////        String QUERRY = "SELECT " + Database.lawTable.COLUMN_LAW + " FROM " + Database.lawTable.TABLE_NAME +
////                " WHERE ";
////    }


}
