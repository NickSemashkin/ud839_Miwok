package com.example.android.miwok;

import android.widget.ArrayAdapter;

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final  int NO_IMAGE_PROVIDED = -1;

    /** Audio resource ID for the word */
    private int mAudioResourceId;

    public Word(String defaultTranslation, String miwokTranslation,int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId  = audioResourceId;

    }


    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId,int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId  = audioResourceId;
    }

    public String getmDefaultTranslation() {

        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }
    public boolean hasImage()
    {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }
}
