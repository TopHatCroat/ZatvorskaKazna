package com.tophatcroat.zatvorskakazna.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by antonio on 26/08/15.
 */
public class LawsModel implements Parcelable{
    int id;
    String law;
    int sentence;

    public LawsModel(int id, String law, int sentence){
        this.id = id;
        this.law = law;
        this.sentence = sentence;
    }

    public LawsModel(String law, int sentence){
        this.id = 0;
        this.law = law;
        this.sentence = sentence;
    }

    protected LawsModel(Parcel in) {
        id = in.readInt();
        law = in.readString();
        sentence = in.readInt();
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

    public void setSentence(int sentence) {
        this.sentence = sentence;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    public String getLaw() {
        return law;
    }

    public int getSentence() {
        return sentence;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(law);
        dest.writeInt(sentence);
    }
}
