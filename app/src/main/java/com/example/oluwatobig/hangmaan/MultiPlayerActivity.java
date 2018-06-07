package com.example.oluwatobig.hangmaan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.example.oluwatobig.hangmaan.R.id.introduceText;

public class MultiPlayerActivity extends AppCompatActivity {

    EditText introducedText;
    String playerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);

        introducedText=(EditText)findViewById(R.id.introduceText);
        playerText=introducedText.getText().toString();
    }

    public void play(View v){
        Intent MPGIntent=new Intent(MultiPlayerActivity.this,MultiPlayerGameActivity.class);
        MPGIntent.putExtra("PLAYERS_WORD",playerText);
        startActivity(MPGIntent);
    }
}
