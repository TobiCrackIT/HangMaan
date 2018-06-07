package com.example.oluwatobig.hangmaan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {
    private Button sPlayerButton,mPlayerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sPlayerButton=(Button)findViewById(R.id.singlePlayer);
        mPlayerButton=(Button)findViewById(R.id.multiPlayer);
    }

    public void startSinglePlayerGame(View v){
        Intent singlePlayerIntent=new Intent(SplashActivity.this,MainActivity.class);
        startActivity(singlePlayerIntent);
    }

    public void startMultiPlayerGame(View v){
        Intent multiPlayerIntent=new Intent(SplashActivity.this,MultiPlayerActivity.class);
        startActivity(multiPlayerIntent);
    }

    public void seeScores(View v){
        Intent scoresIntent=new Intent(SplashActivity.this,ScoresActivity.class);
        startActivity(scoresIntent);
    }
}
