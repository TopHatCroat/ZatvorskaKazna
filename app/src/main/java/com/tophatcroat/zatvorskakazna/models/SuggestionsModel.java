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


    public SuggestionsModel(Context parent, int id, String suggestion, int time, String image){
        this.id = id;
        this.suggestion = suggestion;
        this.time = time;
        this.image = parent.getResources().getIdentifier(image, null, parent.getPackageName());
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
