package com.tophatcroat.zatvorskakazna.models;

import android.content.Context;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import com.tophatcroat.zatvorskakazna.R;
import com.tophatcroat.zatvorskakazna.ui.SuggestionActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SuggestionsModel implements Parcelable {
    int id;
    String suggestion;
    int time;
    int image;
    int amount;


    public SuggestionsModel(Context parent, int id, String suggestion, int time, String image){
        this.id = id;
        this.suggestion = suggestion;
        this.time = time;
        this.image = parent.getResources().getIdentifier(image, null, parent.getPackageName());
    }

    public SuggestionsModel(Context parent, int id, String suggestion, int time, int amount, String image){
        this.id = id;
        this.suggestion = generateSuggestion(suggestion, amount);
        this.amount = amount;
        this.time = time;
        this.image = parent.getResources().getIdentifier(image, null, parent.getPackageName());
    }

    private String generateSuggestion(String suggestion, int amount) {
        String s;
        return "Vrijeme koje bi proveli u pritvoru dovoljno je da si prosječan Hrvat plati " +
                suggestion + ", " + amount + " puta!" + "\nIskoristite svoje vrijeme za nešto bolje.";
    }

    public SuggestionsModel(Context parent, int value){

    }

    protected SuggestionsModel(Parcel in) {
        id = in.readInt();
        suggestion = in.readString();
        time = in.readInt();
        image = in.readInt();
    }

    public static final Creator<SuggestionsModel> CREATOR = new Creator<SuggestionsModel>() {
        @Override
        public SuggestionsModel createFromParcel(Parcel in) {
            return new SuggestionsModel(in);
        }

        @Override
        public SuggestionsModel[] newArray(int size) {
            return new SuggestionsModel[size];
        }
    };

    protected void LawsModel(Parcel in){
        this.id = in.readInt();
        this.suggestion = in.readString();
        this.time = in.readInt();
        this.image = in.readInt();
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
        dest.writeInt(image);
    }
}
