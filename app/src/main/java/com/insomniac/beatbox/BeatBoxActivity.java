package com.insomniac.beatbox;

import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class BeatBoxActivity extends SingleFragmentActivity {

    private static final String TAG = "BeatBoxActivity";

    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }
}
