package com.tophatcroat.zatvorskakazna.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

import com.tophatcroat.zatvorskakazna.R;
import com.tophatcroat.zatvorskakazna.db.DBSource;
import com.tophatcroat.zatvorskakazna.db.Database;
import com.tophatcroat.zatvorskakazna.models.LawsModel;
import com.tophatcroat.zatvorskakazna.models.SuggestionsModel;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by antonio on 30/08/15.
 */
public class SuggestionActivity extends Activity {

    private String TAG = "Suggestion Activity: ";
    private LawsModel law;
    private DBSource dbSource;
    int numOfRows;
    int randomRow;
    int time;
    int totalTime;
    Random random;



    @Bind(R.id.law_tv_suggestion)
    TextView lawTVSuggestion;

    @Bind(R.id.suggestion_tv)
    TextView suggestionTV;


    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestion_activity);
        ButterKnife.bind(this);

        dbSource = new DBSource(this);

        random = new Random();

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            dbSource.open();
            Log.e(TAG, "onResume called");
        } catch (SQLException e){
            e.printStackTrace();
        }

        law = (LawsModel) getIntent().getParcelableExtra("Law");
        lawTVSuggestion.setText(Integer.toString(law.getSentence()));

        numOfRows = (int) dbSource.getSuggestionCount();
        randomRow = random.nextInt(numOfRows);
        Cursor cursor = dbSource.getSuggestion(randomRow);
        cursor.moveToFirst();

        if(cursor.getCount() == 1) {
            int a = cursor.getColumnIndex(Database.suggestionTable.COLUMN_SUGGESTION);
            int b = cursor.getColumnIndex(Database.suggestionTable.COLUMN_TIME);

            time = cursor.getInt(b);

            totalTime = law.getSentence() / time;

            suggestionTV.setText("Prosječan Hrvat za to vrijeme zaradi dovoljno novca da ... \n" +
                    totalTime + " plati " + cursor.getString(a));
        }
        else Log.e(TAG, "no suggestions found");

    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            dbSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
