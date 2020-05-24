package com.castac.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {

    private static final String TAG = "BeatBox";

    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public BeatBox(Context context){
        mAssets = context.getAssets();
        //Deprecated for compatibility
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();

    }

    private void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssets.openFd(sound.getmAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setmSoundId(soundId);
    }

    private void loadSounds(){
        String[] soundsNames;

        try {
            soundsNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found" + soundsNames.length + "sounds");
        } catch (IOException e){
            Log.e(TAG, "Could not list assets", e);
            return;
        }

        for(String filename : soundsNames){
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException e){
                Log.e(TAG, "Could not load sound " + filename, e);
            }

        }
    }

    public List<Sound> getmSounds(){
        return mSounds;
    }

    public void play(Sound sound){
        Integer soundId = sound.getmSoundId();
        if(soundId == null){
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void release(){
        mSoundPool.release();
    }


}
