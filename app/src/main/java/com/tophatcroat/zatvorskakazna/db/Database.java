package com.tophatcroat.zatvorskakazna.db;

public class Database {
    public Database(){}

    public static class lawTable{
        public final static String TABLE_NAME = "laws";
        public final static String COLUMN_ID = "_id";
        public final static String COLUMN_LAW = "law";
        public final static String COLUMN_ARTICLE_NUM = "articleNum";
    }

    public static class aboutLawsTable{
        public final static String TABLE_NAME = "aboutLaws";
        public final static String COLUMN_ID = "_id";
        public final static String COLUMN_SENTENCE = "sentence";
        public final static String COLUMN_ARTICLE_BODY = "articleBody";
        public final static String COLUMN_LAWS_ID = "lawID";
    }

    public static class suggestionTable{
        public final static String TABLE_NAME = "suggestions";
        public final static String COLUMN_ID = "_id";
        public final static String COLUMN_SUGGESTION = "suggestion";
        public final static String COLUMN_VALUE= "value";
        public final static String COLUMN_IMAGE = "image";
    }
}
