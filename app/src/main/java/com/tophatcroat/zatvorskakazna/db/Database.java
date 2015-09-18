package com.tophatcroat.zatvorskakazna.db;

public class Database {
    public Database(){}

    public static class lawTable{
        public final static String TABLE_NAME = "laws";
        public final static String COLUMN_ID = "id";
        public final static String COLUMN_LAW = "law";
        public final static String COLUMN_SENTENCE = "sentence";

        public final static String CREATE = "CREATE TABLE " + TABLE_NAME + " ("+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN_LAW + " TEXT NOT NULL, " +
                COLUMN_SENTENCE + " INTEGER NOT NULL " +
                ");";

        public final static String INSERT_LAWS = "INSERT INTO " + TABLE_NAME + "(" + COLUMN_LAW + ", " + COLUMN_SENTENCE + ")" +
                " SELECT " + "'Obijesna vožnja u cestovnom prometu'" + " AS " + COLUMN_LAW + ", " + "'3'" + " AS " + COLUMN_SENTENCE +
                " UNION SELECT " + "'Krađa', " + "'5'" +
                " UNION SELECT " + "'Neovlaštena proizvodnja droge', " + "'12'" +
                " UNION SELECT " + "'Neovlašten promet drogama', " + "'15'" +
                " UNION SELECT " + "'Omogućavanje trošenja droga', " + "'5'" +
                " UNION SELECT " + "'Onečišćenje okoliša', " + "'5'" +
                " UNION SELECT " + "'Ubijanje ili mučenje životinja', " + "'1'" +
                " UNION SELECT " + "'Zlouporaba radioaktivnih tvari', " + "'5'"
                ;
    }

    public static class suggestionTable{
        public final static String TABLE_NAME = "suggestions";
        public final static String COLUMN_ID = "id";
        public final static String COLUMN_SUGGESTION = "suggestion";
        public final static String COLUMN_TIME = "time";
        public final static String COLUMN_IMAGE = "image";

        public final static String CREATE = "CREATE TABLE " + TABLE_NAME + " ("+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN_SUGGESTION + " TEXT NOT NULL, " +
                COLUMN_TIME + " INTEGER NOT NULL " +
                COLUMN_IMAGE + " TEXT NOT NULL" +
                ");";

        public final static String INSERT_SUGGESTIONS = "INSERT INTO " + TABLE_NAME + "(" + COLUMN_SUGGESTION + ", " + COLUMN_TIME + ")" +
                " SELECT " + "'Neka pizdarija'" + " AS " + COLUMN_SUGGESTION + ", " + "'3'" + " AS " + COLUMN_TIME + ", " + "'drawable/car'" + " AS " + COLUMN_IMAGE +
                " UNION SELECT " + "'Ferrari', " + "'36', " + "'drawable/car'" +
                " UNION SELECT " + "'Putovanje u Ameriku', " + "'6', " + "'drawable/america'" +
                " UNION SELECT " + "'Stan', " + "'60', " + "'drawable/apartment'" +
                " UNION SELECT " + "'Novac', " + "'1', " + "'drawable/money'" +
                " UNION SELECT " + "'iPhonček', " + "'2', " + "'drawable/phone'" +
                " UNION SELECT " + "'Putovanje u Kinu', " + "'6', " + "'drawable/china'" +
                " UNION SELECT " + "'Sat kao u Sanadera', " + "'12', " + "'drawable/watch'"
                ;
    }
}
