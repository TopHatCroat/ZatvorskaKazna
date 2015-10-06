package com.tophatcroat.zatvorskakazna.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonio on 26/08/15.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "zatvorskaKazna.db";
    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_PATH = "/data/data/com.tophatcroat.zatvorskakazna/databases/";
    Context mContext;
    private SQLiteDatabase myDataBase;

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("onCreate", "CALLED");
//        db.beginTransaction();
//        try {
//            Log.i("onCreate", "TRYING");
//
//            db.execSQL(Database.lawTable.CREATE);
//            db.execSQL(Database.lawTable.INSERT_LAWS);
//            db.execSQL(Database.suggestionTable.CREATE);
//            db.execSQL(Database.suggestionTable.INSERT_SUGGESTIONS);
//            db.setTransactionSuccessful();
//            Log.i("onCreate", "SUCCESS");
//
//        } finally {
//            db.endTransaction();
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + Database.lawTable.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + Database.suggestionTable.TABLE_NAME);
//        onCreate(db);
    }

//    public List<LawsModel> read(String search){
////        List<lawsModel> recordsList = new ArrayList<lawsModel>();
////
////        String QUERRY = "SELECT " + Database.lawTable.COLUMN_LAW + " FROM " + Database.lawTable.TABLE_NAME +
////                " WHERE ";
////    }


    /**
     * This method will create database in application package /databases
     * directory when first time application launched
     **/
    public void createDataBase() throws IOException {
        Log.i("createDatabase", "CALLED");
        boolean mDataBaseExist = checkDataBase();
        System.out.println("mDatabaseExists: " + mDataBaseExist);
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException mIOException) {
                mIOException.printStackTrace();
                throw new Error("Error copying database");
            } finally {
                this.close();
            }
        }
    }

    /** This method checks whether database is exists or not **/
    private boolean checkDataBase() {
        try {
            final String mPath = DATABASE_PATH + DATABASE_NAME;
            final File file = new File(mPath);
            if (file.exists())
                return true;
            else
                return false;
        } catch (SQLiteException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method will copy database from /assets directory to application
     * package /databases directory
     **/
    private void copyDataBase() throws IOException {
        try {

            InputStream mInputStream = mContext.getAssets().open(DATABASE_NAME);
            String outFileName = DATABASE_PATH + DATABASE_NAME;
            OutputStream mOutputStream = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = mInputStream.read(buffer)) > 0) {
                mOutputStream.write(buffer, 0, length);
            }
            mOutputStream.flush();
            mOutputStream.close();
            mInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method opens database for operations **/
    public SQLiteDatabase openDataBase() throws SQLException {
        String mPath = DATABASE_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mPath, null,
                SQLiteDatabase.OPEN_READWRITE);
        return myDataBase;
    }

    /** This method closes database connection and releases occupied memory **/
    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        SQLiteDatabase.releaseMemory();
        super.close();
    }


}
