package com.example.lesson2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.sql.Time;
import java.util.Locale;

public class TimerTutorial extends AppCompatActivity {
    private boolean running;
    private TextView timert;
    private int seconds;
    private boolean wasrunning=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        timert=findViewById(R.id.textViewTimer);
        if (savedInstanceState!=null){
        seconds= savedInstanceState.getInt("seconds");
        running= savedInstanceState.getBoolean("running");
        wasrunning=savedInstanceState.getBoolean("wasrunning");
        }
        RunTimer();
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        wasrunning = running;
//        running = false;
//    }
//
//    protected void onStart() {
//        super.onStart();
//        running = wasrunning;
//
//    }

    @Override
    protected void onPause() {
        super.onPause();
        wasrunning=running;
        running=false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        running=wasrunning;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){ //соохранение данных при повороте
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
        outState.getBoolean("wasrunning",wasrunning);
    }
    public void StartTimer(View view) {
        running=true;
    }

    public void EndTimer(View view) {
        running=false;
        seconds=0;

    }

    public void PauseTimer(View view) {
        running=false;
    }
    private void RunTimer(){
        final Handler handler =new Handler();
        handler.post(new Runnable(){
            @Override
            public void run()
            {
                int hours=seconds/3600;
                int minuts=(seconds % 3600)/60;
                int secs=seconds % 60;
                String time =String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minuts,secs);
                timert.setText(time);
                if(running){seconds++; }
                handler.postDelayed(this,1000);
            }

        }
            );

        }
    }

