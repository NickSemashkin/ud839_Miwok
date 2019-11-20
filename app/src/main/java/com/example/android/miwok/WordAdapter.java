package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorResourceId;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View wordItem = convertView;
        if (wordItem == null){
            wordItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word currentWord = getItem(position);

        TextView miwokTextView =(TextView)wordItem.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getmMiwokTranslation());

        TextView defaultTextView =(TextView)wordItem.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getmDefaultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) wordItem.findViewById(R.id.image);
        if (currentWord.hasImage()) {
            // Set the ImageView to the image resource specified in the current Word
            imageView.setImageResource(currentWord.getmImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }else
        {
            imageView.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textContainer = wordItem.findViewById(R.id.text_container);

        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        textContainer.setBackgroundColor(color);

        return wordItem;
    }
    public WordAdapter(Activity context, ArrayList<Word> words,int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }
}
