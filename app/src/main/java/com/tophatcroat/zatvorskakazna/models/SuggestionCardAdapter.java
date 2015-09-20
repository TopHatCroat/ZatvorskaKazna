package com.tophatcroat.zatvorskakazna.models;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tophatcroat.zatvorskakazna.R;

import java.util.List;

public class SuggestionCardAdapter extends RecyclerView.Adapter<SuggestionCardAdapter.suggestionViewHolder> {

    private List<SuggestionsModel> suggestions;

    public SuggestionCardAdapter(List<SuggestionsModel> suggestionList) {
        this.suggestions = suggestionList;
    }

    @Override
    public int getItemCount() {
        return suggestions.size();
    }

    @Override
    public suggestionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.suggestion_card, viewGroup, false);

        return new suggestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(suggestionViewHolder suggestionViewHolder, int i) {
        SuggestionsModel suggestionModel = suggestions.get(i);
        suggestionViewHolder.suggestion_card_title.setText(suggestionModel.suggestion);
        suggestionViewHolder.suggestionCardDescription.setText(Integer.toString(suggestionModel.time));
        suggestionViewHolder.suggestionIv.setImageResource(suggestionModel.image);
    }

    public static class suggestionViewHolder extends RecyclerView.ViewHolder {
        protected TextView suggestionCardDescription;
        protected TextView suggestion_card_title;
        protected ImageView suggestionIv;

        public suggestionViewHolder(View v) {
            super(v);
            suggestionCardDescription = (TextView) v.findViewById(R.id.suggestion_card_description);
            suggestion_card_title = (TextView) v.findViewById(R.id.suggestion_card_title);
            suggestionIv = (ImageView) v.findViewById(R.id.suggestion_iv);

        }
    }
}