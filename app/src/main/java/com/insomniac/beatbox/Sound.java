package com.insomniac.beatbox;

import android.util.Log;

/**
 * Created by DELL on 12/19/2017.
 */

public class Sound {
    private String mAssetPath;
    private String mName;
    private static final String TAG = "Sound";

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

    private Integer mSoundId;

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }

    public Sound(String assetPath){
        mAssetPath = assetPath;
        Log.d(TAG,assetPath);
        String[] components = assetPath.split("/");
        String fileName = components[components.length - 1];
        mName = fileName.replace(".wav","");
    }
}
