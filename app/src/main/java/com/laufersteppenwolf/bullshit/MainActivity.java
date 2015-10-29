package com.laufersteppenwolf.bullshit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "Bullshit";
    public static final String VERSION = "version";

    private static Context context;
    public static SharedPreferences myPreferences;
    public static SharedPreferences.Editor editor;

    private MediaPlayer mpBullshit;
    private MediaPlayer mpMoop;
    private MediaPlayer mpMlg;
    private MediaPlayer mpWtf;
    private MediaPlayer mpPunch;
    private MediaPlayer mpJaaa;

    private MediaPlayer[] mediaPlayers;

    private void stopAll() {
        for (MediaPlayer mp: mediaPlayers) {
            mp.stop();
        }
        Log.d(LOG_TAG, "All MediaPlayers stopped");
    }

    public static void setPreferences(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public static void initPreferences(){
        myPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = myPreferences.edit();
        setPreferences(VERSION, BuildConfig.VERSION_NAME); // Update the build version for About settings
    }

    private void init() {
        mpBullshit = MediaPlayer.create(this, R.raw.bullshit);
        mpMoop = MediaPlayer.create(this, R.raw.moop);
        mpMlg = MediaPlayer.create(this, R.raw.mlg);
        mpWtf = MediaPlayer.create(this, R.raw.wtf);
        mpPunch = MediaPlayer.create(this, R.raw.punch);
        mpJaaa = MediaPlayer.create(this, R.raw.jaaa);

        mediaPlayers = new MediaPlayer[] {mpBullshit, mpMoop, mpMlg, mpWtf, mpPunch, mpJaaa};

        Log.d(LOG_TAG, "All MediaPlayers initialized");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        final Button bullshit = (Button) findViewById(R.id.bullshit);
        final Button moop = (Button) findViewById(R.id.moop);
        final Button mlg = (Button) findViewById(R.id.mlg);
        final Button wtf = (Button) findViewById(R.id.wtf);
        final Button punch = (Button) findViewById(R.id.punch);
        final Button jaaa = (Button) findViewById(R.id.jaaa);

        initPreferences();
        init();


        bullshit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAll();
                try {
                    mpBullshit.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mpBullshit.seekTo(2200);
                mpBullshit.start();
            }
        });

        moop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAll();
                try {
                    mpMoop.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mpMoop.start();
            }
        });

        mlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAll();
                try {
                    mpMlg.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mpMlg.start();
            }
        });

        wtf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAll();
                try {
                    mpWtf.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mpWtf.seekTo(700);
                mpWtf.start();
            }
        });

        punch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAll();
                try {
                    mpPunch.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mpPunch.start();
            }
        });

        jaaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAll();
                try {
                    mpJaaa.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mpJaaa.start();
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();

        for (MediaPlayer mp: mediaPlayers) {
            mp.release();
            mp = null;
        }
        Log.d(LOG_TAG, "All MediaPlayers released");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "onResume");
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            final Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
