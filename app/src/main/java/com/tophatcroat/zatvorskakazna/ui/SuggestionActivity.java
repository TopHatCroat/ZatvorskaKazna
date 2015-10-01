package com.tophatcroat.zatvorskakazna.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.tophatcroat.zatvorskakazna.R;
import com.tophatcroat.zatvorskakazna.db.DBSource;
import com.tophatcroat.zatvorskakazna.db.Database;
import com.tophatcroat.zatvorskakazna.models.LawsModel;
import com.tophatcroat.zatvorskakazna.models.SuggestionCardAdapter;
import com.tophatcroat.zatvorskakazna.models.SuggestionsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by antonio on 30/08/15.
 */
public class SuggestionActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "prefs";
    private static final String AVERAGE_PAY_PREF = "averagePay";
    private String TAG = "Suggestion Activity: ";
    private LawsModel law;
    private DBSource dbSource;
    int numOfRows;
    int randomRow;
    int timeSource;
    int totalTime;
    int averagePayNum;
    String suggestionSource;
    String imageSource;
    SharedPreferences sharedPreferences;
    ActionBar actionBar;

    Random random;

    @Bind(R.id.law_tv_suggestion)
    TextView lawTVSuggestion;
    @Bind(R.id.suggestion_tv)
    TextView suggestion;
    private int totalAmount;

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestion_activity);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        if(!sharedPreferences.contains(AVERAGE_PAY_PREF)){
            //SharedPreferences.Editor editor = sharedPreferences.edit(); //use either "editor" or like bellow
            sharedPreferences.edit().putInt(AVERAGE_PAY_PREF, 5800);
            sharedPreferences.edit().commit();
        }

        averagePayNum = sharedPreferences.getInt(AVERAGE_PAY_PREF, 5800);

        dbSource = new DBSource(this);
        random = new Random();
        law = (LawsModel) getIntent().getParcelableExtra("Law");


        setTitle(law.getLaw());
        actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true); //display the back button

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

        RecyclerView recList = (RecyclerView) findViewById(R.id.card_list);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        SuggestionCardAdapter suggestionCardAdapter = new SuggestionCardAdapter(fillData());
        recList.setAdapter(suggestionCardAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //code for going back from activity
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    private List<SuggestionsModel> fillData(){
        //LinearLayout item = (LinearLayout)findViewById(R.id.suggestion_card_view);
        //View child = getLayoutInflater().inflate(R.layout.suggestion_activity, null);
        List<SuggestionsModel> list = new ArrayList<SuggestionsModel>();

        lawTVSuggestion.setText(Integer.toString(law.getSentence()));

//        numOfRows = (int) dbSource.getSuggestionCount();
//        randomRow = random.nextInt(numOfRows);
//        Cursor cursor = dbSource.getSuggestionById(randomRow);
//        cursor.moveToFirst();

        Cursor cursor = dbSource.getSuggestionByValue(law.getSentence()*averagePayNum); //multiply sentence num. of months with average pay
        cursor.moveToFirst();
        if(cursor.getCount() == 1) {
            try {
                int a = cursor.getColumnIndex(Database.suggestionTable.COLUMN_SUGGESTION);
                int b = cursor.getColumnIndex(Database.suggestionTable.COLUMN_VALUE);
                int c = cursor.getColumnIndex(Database.suggestionTable.COLUMN_IMAGE);

                System.out.println(a + "  " + b + "   " + c);

                suggestionSource = cursor.getString(a);
                timeSource = cursor.getInt(b);
                totalTime = law.getSentence();
                imageSource = cursor.getString(c);
                totalAmount = totalTime*averagePayNum/timeSource;

            } catch (Exception e) {
                System.out.println(e);
                System.out.println(suggestionSource);
                System.out.println(timeSource);
                System.out.println(totalTime);
                System.out.println(imageSource);
            }
            list.add(new SuggestionsModel(this, 0, suggestionSource, timeSource, totalAmount, imageSource));
        }
        else Log.e(TAG, "no suggestions found");

        return list;
    }
}
