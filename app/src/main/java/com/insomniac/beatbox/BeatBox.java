package com.insomniac.beatbox;


import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private AssetManager mAssetManager;

    public List<Sound> getSoundList() {
        return mSoundList;
    }

    private List<Sound> mSoundList = new ArrayList<>();

    public BeatBox(Context context){
        mAssetManager = context.getAssets();
        loadSounds();
    }

    private void loadSounds(){
        String[] soundNames;
        try{
            soundNames = mAssetManager.list(SOUNDS_FOLDER);
            Log.i(TAG,"Found" + soundNames.length + "sounds" );
        }catch (IOException e){
            Log.e(TAG,"Could not load",e);
            return;
        }

        for(String fileName : soundNames){
            String assetPath = SOUNDS_FOLDER + "/" + fileName;
            Sound sound = new Sound(assetPath);
            mSoundList.add(sound);
        }

        Log.d(TAG,mSoundList.get(0).toString());
    }
}
