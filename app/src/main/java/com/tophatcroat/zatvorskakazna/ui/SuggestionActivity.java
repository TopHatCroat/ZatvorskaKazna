package com.tophatcroat.zatvorskakazna.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
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
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitles";
    public static final String CURRENT_SUGGESTION_ID = "current_suggestionID";
    public static final String CURRENT_ARTICLE_NUM = "current_article_num";

    private boolean subtitleShown;
    private String TAG = "Suggestion Activity: ";
    private LawsModel law;
    private DBSource dbSource;
    int timeSource;
    int totalTime;
    int averagePayNum;
    String suggestionSource;
    String imageSource;
    int currentSuggestionID;
    SharedPreferences sharedPreferences;
    ActionBar actionBar;
    TextAnimation textAnimation;
    Random random;
    private int totalAmount;


    @Bind(R.id.law_tv_suggestion)
    TextView lawTVSuggestion;
    @Bind(R.id.suggestion_tv)
    TextView suggestion;
    @Bind(R.id.suggestion_tv_counter)
    TextView suggestionTVCounter;
    @Bind(R.id.suggestion_tv_title)
    TextView suggestionTVTitle;
    @Bind(R.id.article_SV)
    ScrollView articleSV;

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

        setTitle("Detaljni prikaz i prijedlog");
        actionBar = getSupportActionBar();
        actionBar.setElevation(3f);

        //actionBar.setDisplayHomeAsUpEnabled(true); //display the back button

        if(savedInstanceState != null){ //if savedInstanceState exists get the data from it
            subtitleShown = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        fillData();
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

        SuggestionCardAdapter suggestionCardAdapter = new SuggestionCardAdapter(makeSuggestion());
        recList.setAdapter(suggestionCardAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) { //put shit into saved instance state
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, subtitleShown);
        outState.putInt(CURRENT_ARTICLE_NUM, textAnimation.position);
        outState.putInt(CURRENT_SUGGESTION_ID, currentSuggestionID);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.suggestion_activity_menu, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_subtitle);
        if(subtitleShown){
            subtitleItem.setTitle(R.string.remove_subtitles);
        } else {
            subtitleItem.setTitle(R.string.add_subtitles);
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case (R.id.menu_item_subtitle):
                subtitleShown = !subtitleShown;
                this.invalidateOptionsMenu(); //triggers onCreateOptionsMenu()
                if(subtitleShown) this.getSupportActionBar().setSubtitle("Hide Me!");
                else this.getSupportActionBar().setSubtitle(null);
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

    private void fillData() {
        //sets text size in title by the lenght of the law title string
        int size = law.getLaw().length();
        Log.i("STRING SIZE: ", String.valueOf(size));
        if (size > 35) lawTVSuggestion.setTextSize(20f);
        if (size > 70) lawTVSuggestion.setTextSize(16f);
        if (size > 100) lawTVSuggestion.setTextSize(12f);
        lawTVSuggestion.setText(law.getLaw());

        //filling article description text and sending it for animation
        Cursor cursor = dbSource.getAboutLawsByID(law.getId());
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int a = cursor.getColumnIndex(Database.aboutLawsTable.COLUMN_ARTICLE_BODY);
            int b = cursor.getColumnIndex(Database.aboutLawsTable.COLUMN_LAWS_ID);
            int c = cursor.getColumnIndex(Database.aboutLawsTable.COLUMN_SENTENCE);

            law.addArticle(cursor.getString(a));
            law.addSentence(cursor.getInt(c));

            cursor.moveToNext();
        }

        //sending to animation
        textAnimation = new TextAnimation(suggestion, suggestionTVTitle, suggestionTVCounter, law);

        textAnimation.startAnimation();
        suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textAnimation.stopAnimation();
                articleSV.fullScroll(View.FOCUS_UP);
                makeSuggestion();
            }
        });

        cursor.close();
    }


    private List<SuggestionsModel> makeSuggestion(){
        //create a Suggestion card for the article description
        List<SuggestionsModel> suggestionsModels = new ArrayList<SuggestionsModel>();
        //get the suggestion form DB, querry randomises suggestions
        Cursor cursor = dbSource.getSuggestionByValue(law.sentences.get(textAnimation.position)*averagePayNum); //multiply sentence num. of months with average pay

        cursor.moveToFirst();
        if(cursor.getCount() == 1) { //useless checking, this is done in SQL querry
            try {
                int a = cursor.getColumnIndex(Database.suggestionTable.COLUMN_SUGGESTION);
                int b = cursor.getColumnIndex(Database.suggestionTable.COLUMN_VALUE);
                int c = cursor.getColumnIndex(Database.suggestionTable.COLUMN_IMAGE);
                int d = cursor.getColumnIndex(Database.suggestionTable.COLUMN_ID);

                suggestionSource = cursor.getString(a);
                timeSource = cursor.getInt(b);
                totalTime = law.sentences.get(textAnimation.position);
                imageSource = cursor.getString(c);
                totalAmount = totalTime*averagePayNum/timeSource;

                currentSuggestionID = cursor.getInt(d);

            } catch (Exception e) {
                System.out.println(e);
                System.out.println(suggestionSource);
                System.out.println(timeSource);
                System.out.println(totalTime);
                System.out.println(imageSource);
            }
            suggestionsModels.add(new SuggestionsModel(this, 0, suggestionSource, timeSource, totalAmount, imageSource));
        }
        else Log.e(TAG, "error while looking for suggestions");

        cursor.close();
        return suggestionsModels;
    }
}
