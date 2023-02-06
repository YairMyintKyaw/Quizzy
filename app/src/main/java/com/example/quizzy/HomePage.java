package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    public static int timerMillisecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Button level0 = findViewById(R.id.level0);
        Button level1 = findViewById(R.id.level1);
        Button level2 = findViewById(R.id.level2);
        Button record = findViewById(R.id.Record);

        level0.setOnClickListener(this);
        level1.setOnClickListener(this);
        level2.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Button clickedBtn = (Button) v;
        switch (clickedBtn.getText().toString()){
            case "Level 0":
                timerMillisecond=0;
                break;
            case "Level 1":
                timerMillisecond=20;
                break;
            case "Level 2":
                timerMillisecond=10;
                break;
        }
        MediaPlayer.create(HomePage.this,R.raw.btn_click_sound).start();
        Intent intent = new Intent(HomePage.this, QuizPage.class);
        startActivity(intent);
    }
}