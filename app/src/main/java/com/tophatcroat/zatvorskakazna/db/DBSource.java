package com.tophatcroat.zatvorskakazna.db;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;


/**
 * Created by antonio on 29/08/15.
 */
public class DBSource {
    private SQLiteDatabase sqLiteDatabase;
    private DBOpenHelper dbOpenHelper;
    private Context context;

    public DBSource(Context context){
        this.context = context;
        this.dbOpenHelper = new DBOpenHelper(context);
    }

    public void open() throws SQLException{
        sqLiteDatabase = dbOpenHelper.getWritableDatabase();
    }

    public void close() throws SQLException{
        dbOpenHelper.close();
    }

    public Cursor filterLaws(String string){
        String whereClause = Database.lawTable.COLUMN_LAW + " LIKE ?";
        Cursor cursor = sqLiteDatabase.query(
                Database.lawTable.TABLE_NAME,
                new String[] {Database.lawTable.COLUMN_LAW, Database.lawTable.COLUMN_SENTENCE},
                whereClause,
                new String[] {"%" + string + "%"},
                null, null, null);

        return cursor;
    }

    public Cursor getSuggestion(int id){
        String whereClause = Database.suggestionTable.COLUMN_ID + " = ?";
        Cursor cursor = sqLiteDatabase.query(
                Database.suggestionTable.TABLE_NAME,
                new String[] {Database.suggestionTable.COLUMN_SUGGESTION, Database.suggestionTable.COLUMN_TIME},
                whereClause,
                new String[] {Integer.toString(id)},
                null, null, null);

        return cursor;
    }

    public long getSuggestionCount(){
        //return DatabaseUtils.longForQuery(sqLiteDatabase, "SELECT COUNT(*) FROM " + Database.suggestionTable.TABLE_NAME, null);

        return DatabaseUtils.queryNumEntries(sqLiteDatabase, Database.suggestionTable.TABLE_NAME, null, null);
    }
}
