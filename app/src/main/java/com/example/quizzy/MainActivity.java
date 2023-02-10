package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static MediaPlayer bgSong;
    public static boolean isplayingAudio=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playAudio(MainActivity.this,R.raw.bgmusic);
        Button button = findViewById(R.id.GetStartBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MediaPlayer.create(MainActivity.this, R.raw.btn_click).start();

                Intent intent = new Intent(MainActivity.this,HomePage.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
    }

    public static void playAudio(Context c, int id){
        bgSong = MediaPlayer.create(c,id);
        if(!bgSong.isPlaying())
        {
            bgSong.setLooping(true);
            isplayingAudio=true;
            bgSong.start();
        }
    }

    public static void stopAudio(){
        isplayingAudio=false;
        bgSong.stop();
    }
}