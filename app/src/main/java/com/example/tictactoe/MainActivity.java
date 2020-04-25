package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    private int winningPositions[][] = {{0,1,2},{3,4,5}, {6,7,8}, {0,3,6},{1,4,7},{2,5,8},{0,4,8}, {2,4,6}}; // board position combination for winning
    private int status[] = {2,2,2,2,2,2,2,2,2};  // status of each position in board. 0: circle 1: cross  2: empty


    // 0: circle 1: cross  2: empty
    private int activePlayer = 0; // whether activeplayer is 'cross' pr 'circle'
    private boolean gamePlaying = true;  // this is used to keep track whether someone has won and disable the board or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);

        button.setOnClickListener(this);
    }

    public void dropIn(View view){

        ImageView imageView = (ImageView) view;

        int gridPosition = Integer.parseInt(imageView.getTag().toString());

        if(status[gridPosition] == 2  && gamePlaying) {  // checking whether a position is already filled or it's empty.

            imageView.setY(imageView.getY() - 2000); // used for animation

            status[gridPosition] = activePlayer;

            if (activePlayer == 0) {
                imageView.setImageResource(R.drawable.circle);
                activePlayer = 1;
            } else {
                imageView.setImageResource(R.drawable.cross);
                activePlayer = 0;
            }

            imageView.animate().translationYBy(2000).setDuration(300); //used for animation

            for(int []position: winningPositions){ //this loop is used to check whether a player has won or not
                if(status[position[0]] == status[position[1]] && status[position[1]] == status[position[2]] && status[position[0]] != 2){
                    Toast.makeText(this,"winner",Toast.LENGTH_LONG).show();
                    gamePlaying = false;
                    button.setVisibility(View.VISIBLE);
                    break;
                }
            }
        }

    }


    // this method is used for resetting the game to it's initial state
    @Override
    public void onClick(View view) {
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++){
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }

        button.setVisibility(View.INVISIBLE);
        gamePlaying = true;
        activePlayer = 0;
        for(int i=0; i<status.length;i++){
            status[i] = 2;
        }
    }
}
