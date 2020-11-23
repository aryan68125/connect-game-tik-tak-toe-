package com.aditya.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//IMPORTANT NOTE**
/*
import "import androidx.gridlayout.widget.GridLayout;" and not "import android.widget.GridLayout;"
otherwise when you try to reset the ImageView to NULL in a grid layout then your application will just crash
*/

public class MainActivity extends AppCompatActivity {

    // 0:- Yellow, 1:- Red, 2:- empty
    int activePlayer = 0;

    //declaring an array that will keep track of the game and store the state of the imageViews inside the gridView
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //array for winning positions
    int[][] WinningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {2, 4, 6}, {0, 4, 8}};

    TextView textView;

    boolean gameactive = true; //to check whether the game should be active or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView1 = (TextView)findViewById(R.id.textView2);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }
        });
    }

    public void dropin(View view) {
        textView = findViewById(R.id.textView);
        //view will return the imageView id that the user had tapped on inside the gridView layout
        //we will have to type cast view into ImageView to make it work properly
        ImageView counter = (ImageView) view;
        //here all the imageViews will now be accessed by a counter via view which will tell us which imageView was tapped on by user
        Log.i("tag", counter.getTag().toString()); //this will allow us to log the imageView that we have pressed and it will show the tag that we've set in the xml file
        int TappedCounter = Integer.parseInt(counter.getTag().toString()); //getting tag in the string format and the converting it to integer using parseInt method

        //this condition will only allow a counter to be tapped if the counter is empty and has no image inside
        //and the game needs to be active as well
        if (gameState[TappedCounter] == 2 && gameactive) {
            gameState[TappedCounter] = activePlayer; //updating the value inside the array with the active player
            counter.setTranslationY(-2000);// setting the counter off the screen -2000dp
            if (activePlayer == 0) {
                //now setting up the image inside the imageView that was tapped in
                counter.setImageResource(R.drawable.yellow);  //yellow is the image resource id in drawable folder
                activePlayer = 1; //setting up the activePlayer to one so that the control can go to the else statement after the click operation is completed
            } else {
                //now setting up the image inside the imageView that was tapped in
                counter.setImageResource(R.drawable.red);  //red is the image resource id in drawable folder
                activePlayer = 0; //setting up the activePlayer to zero so that the control can go to the if statement after the click operation is completed
            }
            //the above logic that uses activePlayer integers allows us to give player 1 and player 2 alternate chances to make a move
            //in the game
            counter.animate().translationYBy(2000).setDuration(500); //setting up the animation on the tapped imageView

            //now writing the logic to determine which player has won
            for (int[] WinningPositions : WinningPositions) //for loop to traverse through WinningPositions array of arrays
            {
                //now applying the conditions for a player to win the game
                if (gameState[WinningPositions[0]] == gameState[WinningPositions[1]] && gameState[WinningPositions[1]] == gameState[WinningPositions[2]] && gameState[WinningPositions[0]] != 2) { //the above if statement tells us that someone has won
                    gameactive = false; //when someone has won game will be deactivated
                    String winner = "";

                    if (activePlayer == 1) //note we have already changed the value of active player when we applied the logic giving for alternative
                    {                   //chances to the players so here the value we will check for is inverted
                        winner = "Yellow has won!!!";
                        textView.setText(winner);
                    } else {
                        winner = "Red has won!!!";
                        textView.setText(winner);
                    }
                    textView.setVisibility(View.VISIBLE);
                    Button button = (Button) findViewById(R.id.button);
                    button.setVisibility(View.VISIBLE); //this will make the button visible when the game ends and someone has won
                }
            }
        }
    }

    public void playAgain(View view) {
        textView.setVisibility(View.INVISIBLE);
        Button button = (Button) findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);

        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);

        //Reseting the imageView to NULL while accessing the imageViews in a gridView via loop
        for (int i = 0; i < grid.getChildCount(); i++) {
            ImageView counter = (ImageView) grid.getChildAt(i);
            counter.setImageDrawable(null);
        }

        //resetting the gamestate array
        for(int j=0;j<gameState.length;j++)
        {
            gameState[j]=2;
        }

        gameactive =true;
        activePlayer=0;
    }
}