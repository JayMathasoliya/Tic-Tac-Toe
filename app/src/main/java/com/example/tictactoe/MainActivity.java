package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    // Player Representation :
    // Player 1  - X
    // Player 2  - O
    int activePlayer = 0;       // This means player 1's turn
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State Meaning :
    // Player 1  - X
    // Player 2  - O
    // 2 - Null
    int [][] winPos = {{0,1,2}, {3,4,5}, {6,7,8},
                        {0,3,6}, {1,4,7}, {2,5,8},
                        {0,4,8}, {2,4,6} };

    @SuppressLint("SetTextI18n")
    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage] == 2){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0)
            {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("Player 2's Turn - Tap to play");
            }
            else
            {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("Player 1's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
            for(int[] winPosition : winPos){

                if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[0]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 2){
                    String winnerStr;
                    gameActive = false;
                    if(gameState[winPosition[0]] == 0){
                        winnerStr = "Player 1 has won";
                        LinearLayout window = findViewById(R.id.window);
                        window.setTranslationX(-1000f);
                        window.animate().translationXBy(1000f).setDuration(1000);
                        window.setVisibility(View.VISIBLE);
                    }
                    else{
                        winnerStr = "Player 2 has won";
                        LinearLayout window = findViewById(R.id.window);
                        window.setTranslationX(-1000f);
                        window.animate().translationXBy(1000f).setDuration(1000);
                        window.setVisibility(View.VISIBLE);
                    }
                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);
                    TextView status2 = findViewById(R.id.status2);
                    status2.setText(winnerStr);
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void gameReset(View view){
        activePlayer = 0;
        gameActive = true;
        Arrays.fill(gameState, 2);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        LinearLayout window = findViewById(R.id.window);
        window.setVisibility(View.GONE);
        TextView status = findViewById(R.id.status);
        status.setText("Player 1's Turn - Tap to play");
    }
    public void gameExit(View view){
        finish();
        System.exit(0);
    }
    public void noWon(View view){
            if(gameState[0] != 2 && gameState[1] != 2 && gameState[2] != 2
                    && gameState[3] != 2 && gameState[4] != 2 && gameState[5] != 2
                    && gameState[6] != 2 && gameState[7] != 2 && gameState[8] != 2) {
                gameReset(view);
            }
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}