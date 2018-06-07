package com.example.oluwatobig.hangmaan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {
    private int noOfFails=0;
    private int noOfCorrectGuesses=0;
    private int pointsWon=0;
    private String ourWord;
    private String wrongGuesses="";
    private EditText userGuess;
    private Button checkButton;
    private ImageView backgroundImage;
    private TextView wrongTextView;
    private LinearLayout correctGuessLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNewWord();

        userGuess=(EditText)findViewById(R.id.guessEditText);
        checkButton=(Button)findViewById(R.id.checkButton);
    }

    public void inputLetter(View v){
        String userInput=userGuess.getText().toString();
        if (userInput.length()==1){
            checkLetter(userInput);
        }else
            Toast.makeText(getApplicationContext(),"Please enter a letter",Toast.LENGTH_SHORT).show();
    }

    public void checkLetter(String letter){

        boolean guessedRight=false;
        char ourGuess=letter.charAt(0);

        for(int i=0;i<ourWord.length();i++){

            if (ourWord.charAt(i)==ourGuess){
                guessedRight=true;
                showLetter(i,ourGuess);
                noOfCorrectGuesses++;
            }
        }

        if (guessedRight==false){
            failLetter(ourGuess);
        }
        userGuess.setText("");

        if (noOfCorrectGuesses==ourWord.length()){
            pointsWon+=5;
            clearScreen();
            setNewWord();
        }
    }

    public void failLetter(char wrongGuess){
        noOfFails++;

        String wrongString=Character.toString(wrongGuess);
        wrongGuesses+=wrongString;

        wrongTextView=(TextView)findViewById(R.id.wrongGuess);
        wrongTextView.setText(wrongGuesses);

        backgroundImage=(ImageView)findViewById(R.id.background_image);

        if (noOfFails==1){
            backgroundImage.setImageResource(R.drawable.hangdroid_1);
        }else if (noOfFails==2){
            backgroundImage.setImageResource(R.drawable.hangdroid_2);
        }else if (noOfFails==3){
            backgroundImage.setImageResource(R.drawable.hangdroid_3);
        }else if (noOfFails==4){
            backgroundImage.setImageResource(R.drawable.hangdroid_4);
        }else if (noOfFails==5){
            backgroundImage.setImageResource(R.drawable.hangdroid_5);
        }else{
            Intent gameOverIntent=new Intent(MainActivity.this,GameOverActivity.class);
            gameOverIntent.putExtra("POINTS",pointsWon);

            startActivity(gameOverIntent);
            finish();
        }
    }

    public void showLetter(int position, char correctGuess){
        correctGuessLayout=(LinearLayout)findViewById(R.id.correctGuessLayout);
        TextView theTextView=(TextView)correctGuessLayout.getChildAt(position);
        theTextView.setText(Character.toString(correctGuess));
    }

    public void clearScreen(){
        //Toast.makeText(this,"CORRECT!",Toast.LENGTH_SHORT).show();
        noOfFails=0;
        noOfCorrectGuesses=0;
        wrongGuesses="";
        wrongTextView.setText("");
        backgroundImage.setImageResource(R.drawable.hangdroid_0);

        for(int position=0;position<ourWord.length();position++){
            TextView currentTv=(TextView)correctGuessLayout.getChildAt(position);
            currentTv.setText("_");
        }

    }

    public void setNewWord(){
        String words="ABLE BEAR CARE DEAR DARE EACH FACE GATE GONE FILE LITE LOVE LIKE KILL CALL CULL CELL DIAL DEAL ISLE" +
                "MEAL LAME NAME NONE HATE THAT THEE WOLF GOAT CROW SEAT HOME VOTE WILL WAIL WELL WALL ALLY DEBT BEND BOMB BORN" +
                "BROW GAME HANG MOON LUNG";
        String[] wordsList=words.split(" ");
        //Random random=new Random(wordsList.length);
        int index=(int)(Math.random()*wordsList.length);
        ourWord=wordsList[index];
    }
}
