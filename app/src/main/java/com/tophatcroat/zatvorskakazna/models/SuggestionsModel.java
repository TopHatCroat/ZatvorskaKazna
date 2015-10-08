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
    int sentenceTime;
    int id;
    String suggestion;
    int value;
    int image;
    int amount;


    public SuggestionsModel(Context parent, int id, String suggestion, int value, String image){
        this.id = id;
        this.suggestion = suggestion;
        this.value = value;
        this.image = parent.getResources().getIdentifier(image, null, parent.getPackageName());
    }

    public SuggestionsModel(Context parent, int id, String suggestion, int value, int amount, String image){
        this.id = id;
        this.amount = amount;
        this.sentenceTime = sentenceTime;
        this.value = value;
        this.image = parent.getResources().getIdentifier(image, null, parent.getPackageName());
        this.suggestion = generateSuggestion(suggestion);
    }

    private String generateSuggestion(String suggestion) {
        return "Vrijeme koje bi proveli u pritvoru dovoljno je da si prosječan Hrvat plati " +
                suggestion + ", " + amount + " puta!" + "\nIskoristite svoje vrijeme za nešto bolje.";
    }

    protected SuggestionsModel(Parcel in) {
        id = in.readInt();
        suggestion = in.readString();
        value = in.readInt();
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
        this.value = in.readInt();
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
        dest.writeInt(value);
        dest.writeInt(image);
    }
}
