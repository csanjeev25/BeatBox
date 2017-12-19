package com.insomniac.beatbox;


import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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
    private static final int MAX_SOUNDS = 7;
    private SoundPool mSoundPool;


    public List<Sound> getSoundList() {
        return mSoundList;
    }

    private List<Sound> mSoundList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BeatBox(Context context){
        mAssetManager = context.getAssets();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build();
            mSoundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(MAX_SOUNDS).build();
        }else{
            mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
        }
        loadSounds();
    }

    private void load(Sound sound) throws IOException{
        AssetFileDescriptor assetFileDescriptor = mAssetManager.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(assetFileDescriptor,1);
        sound.setSoundId(soundId);
    }

    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if(soundId == null)
            return;
        mSoundPool.play(soundId,1.0f,1.0f,1,1,1.0f);
    }

    public void release(){
        mSoundPool.release();
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
            try {
                String assetPath = SOUNDS_FOLDER + "/" + fileName;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSoundList.add(sound);
            }catch (IOException e){
                Log.e(TAG,"Could not load sound" + fileName,e);
            }
        }

        Log.d(TAG,mSoundList.get(0).toString());
    }
}
