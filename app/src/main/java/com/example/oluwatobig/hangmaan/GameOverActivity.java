package com.example.oluwatobig.hangmaan;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.oluwatobig.hangmaan.R.string.points;

public class GameOverActivity extends AppCompatActivity {

    private String playerName;
    int point;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        point =getIntent().getIntExtra("POINTS",0);
        TextView pointsTV=(TextView)findViewById(R.id.pointsDigit);

        pointsTV.setText(Integer.toString(point));
    }

    public void saveScore(View V){
        EditText playerNameTV=(EditText)findViewById(R.id.playerName);
        playerName=playerNameTV.getText().toString().trim();

        SharedPreferences sharedPreferences=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        String previousString=sharedPreferences.getString("DETAILS","");
        editor.putString("DETAILS",playerName+" "+point+" POINTS \n"+previousString);
        editor.commit();
        finish();
    }
}
