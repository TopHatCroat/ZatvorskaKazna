package com.tophatcroat.zatvorskakazna.models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tophatcroat.zatvorskakazna.R;
import com.tophatcroat.zatvorskakazna.ui.SuggestionActivity;

import java.util.ArrayList;

/**
 * Created by antonio on 30/08/15.
 */
public class ListAdapter extends ArrayAdapter<LawsModel> {
    private Context context;

    public ListAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public ListAdapter(Context context, int resource, ArrayList<LawsModel> items){
        super(context, resource, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent){
        View v = convertView;

        if(v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item, null);
        }

        final LawsModel p = getItem(position);

        if(p != null) {
            TextView lawsTV =  (TextView) v.findViewById(R.id.law_text_view);
            //TextView sentenceTV =  (TextView) v.findViewById(R.id.sentence_text_view);

            if(lawsTV != null){
                lawsTV.setText(p.getLaw());
            }
//            if(sentenceTV != null){
//                sentenceTV.setText(Integer.toString(p.getSentence()));
//            }
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), SuggestionActivity.class);
                intent.putExtra("Law", p);
                parent.getContext().startActivity(intent);
            }
        });

        return v;

    }
}
