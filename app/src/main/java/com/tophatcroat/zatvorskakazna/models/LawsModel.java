package com.tophatcroat.zatvorskakazna.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;


public class LawsModel implements Parcelable{
    int id;
    String law;
    int articleNum;
    public ArrayList<String> articles;
    public ArrayList<Integer> sentences;

    public LawsModel(int id, String law, int articleNum){
        this.id = id;
        this.law = law;
        this.articleNum = articleNum;
    }

    public LawsModel(String law, int articleNum){
        this.id = 0;
        this.law = law;
        this.articleNum = articleNum;
    }

    protected LawsModel(Parcel in) {
        id = in.readInt();
        law = in.readString();
        articleNum = in.readInt();
    }

    public static final Creator<LawsModel> CREATOR = new Creator<LawsModel>() {
        @Override
        public LawsModel createFromParcel(Parcel in) {
            return new LawsModel(in);
        }

        @Override
        public LawsModel[] newArray(int size) {
            return new LawsModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setArticleNum(int articleNum) {
        this.articleNum = articleNum;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    public String getLaw() {
        return law;
    }

    public int getArticleNum() {return articleNum;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(law);
        dest.writeInt(articleNum);
    }

    public void addArticle(String article){
        if (articles == null) {
            articles = new ArrayList<>();
        }
        articles.add(article);
    }

    public void addSentence(int sentence){
        if (sentences == null) {
            sentences = new ArrayList<>();
        }
        sentences.add(sentence);
    }
}
