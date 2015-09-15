package com.tophatcroat.zatvorskakazna;

import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.tophatcroat.zatvorskakazna.db.DBSource;
import com.tophatcroat.zatvorskakazna.db.Database;
import com.tophatcroat.zatvorskakazna.models.LawsModel;
import com.tophatcroat.zatvorskakazna.models.ListAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity: ";

    private DBSource dbSource;
    private ArrayList<LawsModel> arrayList;

    @Bind(R.id.autoCompleteTextView)
    EditText editText;

    @Bind(R.id.list_view_zakoni)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);                     //mislim da je
        dbSource = new DBSource(MainActivity.this); //ovo dvoje isto

        arrayList = new ArrayList<LawsModel>();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateList(dbSource.filterLaws(editText.getText().toString()));
                Log.e(TAG, "edited text");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Log.e(TAG, "onCreate called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            dbSource.open();
            updateList(dbSource.filterLaws(editText.getText().toString()));
            Log.e(TAG, "onResume called");
        } catch (SQLException e){
            e.printStackTrace();
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        super.setRequestedOrientation(requestedOrientation);
    }

    public void updateList(Cursor cursor){
        arrayList.clear();

        cursor.moveToFirst();
        int a = cursor.getColumnIndex(Database.lawTable.COLUMN_LAW);
        int b = cursor.getColumnIndex(Database.lawTable.COLUMN_SENTENCE);
        while(!cursor.isAfterLast()){

            String law = cursor.getString(a);
            int sentence = cursor.getInt(b);

            arrayList.add(new LawsModel(law, sentence));
            cursor.moveToNext();
        }

        ListAdapter adapter = new ListAdapter(MainActivity.this,
                R.layout.list_item, arrayList);

        listView.setAdapter(adapter);

    }
}
