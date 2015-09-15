package com.tophatcroat.zatvorskakazna.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by antonio on 11/09/15.
 */
public class SuggestionsModel implements Parcelable {
    int id;
    String suggestion;
    int time;

    public SuggestionsModel(int id, String suggestion, int time){
        this.id = id;
        this.suggestion = suggestion;
        this.time = time;
    }

    protected void LawsModel(Parcel in){
        this.id = in.readInt();
        this.suggestion = in.readString();
        this.time = in.readInt();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(suggestion);
        dest.writeInt(time);
    }
}
