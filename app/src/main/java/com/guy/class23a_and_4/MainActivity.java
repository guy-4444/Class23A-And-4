package com.guy.class23a_and_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    private MaterialTextView main_LBL_steps;

    private StepDetector stepDetector;
    BackgroundSound mBackgroundSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_LBL_steps = findViewById(R.id.main_LBL_steps);


        stepDetector = new StepDetector(this, callBack_steps);
    }

    private StepDetector.CallBack_steps callBack_steps = new StepDetector.CallBack_steps() {
        @Override
        public void oneStep() {
            main_LBL_steps.setText("" + stepDetector.stepCounter);
        }

        @Override
        public void yemenStep() {
            main_LBL_steps.setText("" + stepDetector.stepCounter);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mBackgroundSound = new BackgroundSound();
        mBackgroundSound.execute();
        stepDetector.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBackgroundSound.cancel(true);
        stepDetector.stop();
    }

    public class BackgroundSound extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.msc_yemen_music);
            player.setLooping(true); // Set looping
            player.setVolume(1.0f, 1.0f);
            player.start();

            return null;
        }

    }

}