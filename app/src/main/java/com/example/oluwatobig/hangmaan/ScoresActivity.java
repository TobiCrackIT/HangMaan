package com.example.oluwatobig.hangmaan;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScoresActivity extends AppCompatActivity {

    private TextView scoresTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        scoresTV=(TextView)findViewById(R.id.recordTV);
        SharedPreferences sharedPreferences=getSharedPreferences("MYPREF",MODE_PRIVATE);
        String newScore=sharedPreferences.getString("DETAILS","NO NAME");
        scoresTV.setText(newScore);

    }


}
