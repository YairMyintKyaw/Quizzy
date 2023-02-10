package com.example.quizzy;

import static com.example.quizzy.R.drawable.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    public static int timerMillisecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button level0 = findViewById(R.id.level0);
        Button level1 = findViewById(R.id.level1);
        Button level2 = findViewById(R.id.level2);

        RelativeLayout about = findViewById(R.id.About);
        RelativeLayout music = findViewById(R.id.music);
        ImageView musicIcon = findViewById(R.id.musicIcon);
        Button exitBtn = findViewById(R.id.exit);

        level0.setOnClickListener(this);
        level1.setOnClickListener(this);
        level2.setOnClickListener(this);

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomePage.this.finishAffinity();
                System.exit(0);
            }
        });

        if(MainActivity.isplayingAudio){
            musicIcon.setImageResource(R.drawable.music);

        }else{
            musicIcon.setImageResource(R.drawable.no_music);


        }

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,About.class);
                startActivity(intent);
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.isplayingAudio){
                    MainActivity.stopAudio();
                    musicIcon.setImageResource(R.drawable.no_music);

                }else{
                    MainActivity.playAudio(HomePage.this,R.raw.bgmusic);
                    musicIcon.setImageResource(R.drawable.music);

                }
            }
        });

    }

    @Override
    public void onBackPressed() {
    }


    @Override
    public void onClick(View v) {
        Button clickedBtn = (Button) v;
        QuizPage.score=0;
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
        MediaPlayer.create(HomePage.this,R.raw.btn_click).start();
        Intent intent = new Intent(HomePage.this, QuizPage.class);
        startActivity(intent);
    }
}