package com.shayekh.dice_nested_completed;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int point1=0;
    int point2=0;
    int mIndex;
    ProgressBar mProgressBar;
    final int PROGRESS_BAR_INCREMENT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Link the views in the layout xml file to the java code
        final ImageView leftDice = (ImageView) findViewById(R.id.image_leftDice);
        final ImageView rightDice = (ImageView) findViewById(R.id.image_rightDice);

        Button firstButton = (Button) findViewById(R.id.rollButton1);
        Button secondButton= (Button) findViewById(R.id.rollButton2);
        Button reset=(Button)findViewById(R.id.reset);
        mProgressBar=(ProgressBar)findViewById(R.id.progress_bar);

        // Store the dice images in an array (collection)
        final int[] diceArray = new int[] {
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6};
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        // Tell the button to listen for clicks
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This code gets executed when the button is pressed

                // Create a random number generator
                Random randomNumberGenerator = new Random();

                // Make the random number generator spit out a new random number
                int number = randomNumberGenerator.nextInt(6);

                // Print this random number to the logcat to see it in the Android monitor
                Log.d("Dicee", "The number is " + number );

                // grab a random dice image from the diceArray. Then tell the ImageView to display
                // this image
                leftDice.setImageResource(diceArray[number]);
                if(diceArray[number]==R.drawable.dice1)
                {
                    point1+=1;
                    displayForTeamA(point1);

                }
                else if(diceArray[number]==R.drawable.dice2) {
                    point1+= 2;
                    displayForTeamA(point1);
                }

                else if(diceArray[number]==R.drawable.dice3) {
                    point1+= 3;
                    displayForTeamA(point1);
                }
                else if(diceArray[number]==R.drawable.dice4) {
                    point1+= 4;
                    displayForTeamA(point1);
                }
                else if(diceArray[number]==R.drawable.dice5) {
                    point1+= 5;
                    displayForTeamA(point1);
                }
                else if(diceArray[number]==R.drawable.dice6) {
                    point1+= 6;
                    displayForTeamA(point1);
                }

                updateGame();


            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new random number
                Random randomNumberGenerator = new Random();

                // Make the random number generator spit out a new random number
                int number = randomNumberGenerator.nextInt(6);
                // Set the right dice image using an image from the diceArray.
                rightDice.setImageResource(diceArray[number]);

                if(diceArray[number]==R.drawable.dice1)
                {
                    point2+=1;
                    displayForTeamB(point2);

                }
                else if(diceArray[number]==R.drawable.dice2) {
                    point2+= 2;
                    displayForTeamB(point2);
                }

                else if(diceArray[number]==R.drawable.dice3) {
                    point2+= 3;
                    displayForTeamB(point2);
                }
                else if(diceArray[number]==R.drawable.dice4) {
                    point2+= 4;
                    displayForTeamB(point2);
                }
                else if(diceArray[number]==R.drawable.dice5) {
                    point2+= 5;
                    displayForTeamB(point2);
                }
                else if(diceArray[number]==R.drawable.dice6) {
                    point2+= 6;
                    displayForTeamB(point2);
                }
                updateGame();
            }
        });


    }
    public void reset(){
        point1=0;
        point2=0;
        displayForTeamA(point1);
        displayForTeamB(point2);
        mProgressBar.setProgress(0);
    }
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.score1);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.score2);
        scoreView.setText(String.valueOf(score));
    }


    private void updateGame() {
        // This takes the modulus. Not a division.
        mIndex = (mIndex + 1) % 20;

        // Present an alert dialog if we reach the end.
        if(mIndex == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            if(point1>point2) {
                alert.setMessage("First Player Wins");
            }
            else if(point2>point1)
            {
                alert.setMessage("Second Player Wins");
            }
            else if(point1==point2)
            {
                alert.setMessage("It's a tie");
            }

            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.setNegativeButton("Play Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    reset();
                }
            });
            alert.show();
        }


        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);

    }

}
