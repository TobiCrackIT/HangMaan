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

import static com.example.oluwatobig.hangmaan.R.id.correctGuessLayout;

public class MultiPlayerGameActivity extends AppCompatActivity {

    private int noOfFails=0;
    private int noOfCorrectGuesses=0;
    private int pointsWon=0;
    private String wrongGuesses="";
    String multiPlayerWord;
    TextView wrongTextView;
    private ImageView backgroundImage;
    private EditText userGuess;
    private Button checkButton;
    private LinearLayout correctWordsLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player_game);

        Bundle bundle=getIntent().getExtras();
        multiPlayerWord=bundle.getString("PLAYERS_WORD");
        userGuess=(EditText)findViewById(R.id.guessEditText);
        checkButton=(Button)findViewById(R.id.checkButton);
        wrongTextView=(TextView)findViewById(R.id.wrongGuess);

        wrongTextView.setText(multiPlayerWord);
        //Toast.makeText(getApplicationContext(),"Ho ho ho",Toast.LENGTH_SHORT).show();
        //Log.d("SENT",multiPlayerWord);
        Log.e("MPG",multiPlayerWord);
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

        for(int i=0;i<multiPlayerWord.length();i++){

            if (multiPlayerWord.charAt(i)==ourGuess){
                guessedRight=true;
                //showLetter(i,ourGuess);
                noOfCorrectGuesses++;
            }
        }

        if (guessedRight==false){
            failLetter(ourGuess);
        }
        userGuess.setText("");

        if (noOfCorrectGuesses==multiPlayerWord.length()){
            pointsWon+=5;
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
            Intent gameOverIntent=new Intent(MultiPlayerGameActivity.this,GameOverActivity.class);
            gameOverIntent.putExtra("POINTS",pointsWon);
            startActivity(gameOverIntent);
        }
    }

    public void createTextViews(){
        correctWordsLinearLayout=(LinearLayout)findViewById(R.id.correctWordsLayout);
        for (int x=0;x<multiPlayerWord.length();x++){
            //correctWordsLinearLayout.addView(,x);
        }
    }

    /*public void showLetter(int position, char correctGuess){
        correctGuessLayout=(LinearLayout)findViewById(correctGuessLayout);
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

    }*/
}
