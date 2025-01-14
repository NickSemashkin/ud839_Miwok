/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange ==  AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mMediaPlayer.start();
            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }

        }
    };
    @Override
    protected void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        // Create an array of words

        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

         final ArrayList<Word> words = new ArrayList<Word>();



        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));



        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));



        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));



        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));



        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));



        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));



        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));



        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));



        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));



        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));



        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The

        // adapter knows how to create layouts for each item in the list, using the

        // simple_list_item_1.xml layout resource defined in the Android framework.

        // This list item layout contains a single {@link TextView}, which the adapter will set to

        // display a single word.

        WordAdapter itemsAdapter =

                new WordAdapter(this,words,R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.

        // There should be a {@link ListView} with the view ID called list, which is declared in the

        // word_listyout file.

        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the

        // {@link ListView} will display list items for each word in the list of words.

        // Do this by calling the setAdapter method on the {@link ListView} object and pass in

        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                    // Get the {@link Word} object at the given position the user clicked on
                    Word word = words.get(position);

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }

            });

    }
}
