package com.tophatcroat.zatvorskakazna.db;

public class Database {
    public Database(){}

    public static class lawTable{
        public final static String TABLE_NAME = "laws";
        public final static String COLUMN_ID = "_id";
        public final static String COLUMN_LAW = "law";
        public final static String COLUMN_SENTENCE = "sentence";

        public final static String CREATE = "CREATE TABLE " + TABLE_NAME + " ("+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN_LAW + " TEXT NOT NULL, " +
                COLUMN_SENTENCE + " INTEGER NOT NULL " +
                ");";

        public final static String INSERT_LAWS = "INSERT INTO " + TABLE_NAME + "(" + COLUMN_LAW + ", " + COLUMN_SENTENCE + ")" +
                " SELECT " + "'Obijesna vožnja u cestovnom prometu'" + " AS " + COLUMN_LAW + ", " + "'36'" + " AS " + COLUMN_SENTENCE +
                " UNION SELECT " + "'Krađa', " + "'60'" +
                " UNION SELECT " + "'Neovlaštena proizvodnja droge', " + "'144'" +
                " UNION SELECT " + "'Neovlašten promet drogama', " + "'180'" +
                " UNION SELECT " + "'Omogućavanje trošenja droga', " + "'60'" +
                " UNION SELECT " + "'Onečišćenje okoliša', " + "'60'" +
                " UNION SELECT " + "'Ubijanje ili mučenje životinja', " + "'12'" +
                " UNION SELECT " + "'Zlouporaba radioaktivnih tvari', " + "'60'"
                ;
    }

    public static class aboutLawsTable{
        public final static String TABLE_NAME = "aboutLaws";
        public final static String COLUMN_ARTICLE_NUM = "articleNum";
        public final static String COLUMN_LINK = "link";
        public final static String COLUMN_ARTICLE_BODY = "articleBody";
        public final static String COLUMN_LAWS_ID = "lawID";
    }

    public static class suggestionTable{
        public final static String TABLE_NAME = "suggestions";
        public final static String COLUMN_ID = "_id";
        public final static String COLUMN_SUGGESTION = "suggestion";
        public final static String COLUMN_VALUE= "value";
        public final static String COLUMN_IMAGE = "image";

        public final static String CREATE = "CREATE TABLE " + TABLE_NAME + " ("+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN_SUGGESTION + " TEXT NOT NULL, " +
                COLUMN_VALUE + " INTEGER NOT NULL, " +
                COLUMN_IMAGE + " TEXT NOT NULL" +
                ");";

        public final static String INSERT_SUGGESTIONS = "INSERT INTO " + TABLE_NAME + "(" + COLUMN_SUGGESTION + ", " + COLUMN_VALUE + ", " + COLUMN_IMAGE + ")" +
                " SELECT " + "'Neka pizdarija'" + " AS " + COLUMN_SUGGESTION + ", " + "'5800'" + " AS " + COLUMN_VALUE + ", " + "'drawable/car'" + " AS " + COLUMN_IMAGE +
                " UNION SELECT " + "'Ferrari', " + "'2000000', " + "'drawable/car'" +
                " UNION SELECT " + "'Putovanje u Ameriku', " + "'10000', " + "'drawable/america'" +
                " UNION SELECT " + "'Stan', " + "'900000', " + "'drawable/apartment'" +
                " UNION SELECT " + "'Novac', " + "'1', " + "'drawable/money'" +
                " UNION SELECT " + "'iPhonček', " + "'5000', " + "'drawable/phone'" +
                " UNION SELECT " + "'Putovanje u Kinu', " + "'10000', " + "'drawable/china'" +
                " UNION SELECT " + "'Sat kao u Sanadera', " + "'4000', " + "'drawable/watch'"
                ;
    }
}
