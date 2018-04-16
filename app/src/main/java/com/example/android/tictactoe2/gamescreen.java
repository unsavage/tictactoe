package com.example.android.tictactoe2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class gamescreen extends AppCompatActivity implements View.OnClickListener {

    public static Boolean turnX = true;
    public static Boolean turnO = false;

    public static Boolean xFirst = true;

    public static int pointPlayer1 = 0;
    public static int pointPlayer2 = 0;
    public static int roundCount = 0;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private int grid = 3;
    private Button[][] buttons = new Button[grid][grid];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);
        String savedData = getIntent().getStringExtra("game_type");

        /*
        * Binding the buttons to the toggle function
        * */

        for (int i = 0; i < grid; i++) {
            for (int j = 0; j < grid; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        initialize();
    }

    @Override
    public void onClick(View v) {
        toggle(v);
        if (checkForWin()) celebrate();
        if (gamescreen.roundCount > 8) draw();
        nextTurn();
    }

    public void initialize() {

        textViewPlayer1 = findViewById(R.id.player1);
        textViewPlayer2 = findViewById(R.id.player2);
        Button reset = findViewById(R.id.reset_button);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetScore();
            }
        });
        askCaptain();
    }

    public void askCaptain() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Picking turns");
        alert.setMessage("Who is player 1 ?");

        alert.setPositiveButton("O",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        gamescreen.xFirst = false;

                        gamescreen.turnX = false;
                        gamescreen.turnO = true;
                    }
                });

        alert.setNegativeButton("X", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                gamescreen.xFirst = true;
                gamescreen.turnX = true;
                gamescreen.turnO = false;
            }
        });


        alert.show();
    }

    public void toggle(View view) {
        if (!((Button) view).getText().toString().equals("")) return;

        if (gamescreen.turnX) ((Button) view).setText("X");
        else if (gamescreen.turnO) ((Button) view).setText("O");

        gamescreen.roundCount += 1;
    }


    public void nextTurn() {
        gamescreen.turnX = !gamescreen.turnX;
        gamescreen.turnO = !gamescreen.turnO;
    }

    public void celebrate() {
        String winner = null;
        if (gamescreen.turnX) winner = "X";
        else if (gamescreen.turnO) winner = "O";

        Toast.makeText(this, winner + " wins!", Toast.LENGTH_SHORT).show();

        updatePoints();
        resetBoard();
    }

    public void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    public void updatePoints() {

        if (gamescreen.xFirst && gamescreen.turnX) gamescreen.pointPlayer1 += 1;
        else if (gamescreen.xFirst && gamescreen.turnO) gamescreen.pointPlayer2 += 1;
        else if (!gamescreen.xFirst && gamescreen.turnX) gamescreen.pointPlayer2 += 1;
        else if (!gamescreen.xFirst && gamescreen.turnO) gamescreen.pointPlayer1 += 1;

        refreshScore();

    }

    public void refreshScore() {
        textViewPlayer1.setText("Player 1 : " + gamescreen.pointPlayer1);
        textViewPlayer2.setText("Player 2 : " + gamescreen.pointPlayer2);
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        gamescreen.roundCount = 0;
    }

    public void resetScore() {
        gamescreen.pointPlayer2 = 0;
        gamescreen.pointPlayer1 = 0;
        refreshScore();
        resetBoard();
        askCaptain();
    }

    public Boolean checkForWin() {
        String[][] field = new String[3][3];

        /**
         * this goes though all the buttons and saves the in a string array
         */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        /**
         * this string array goes thought all the rows and columns
         */
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        /**
         * goes though the diagonals of the array
         */
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        return field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//
//        outState.putInt("roundCount", roundCount);
//        outState.putInt("player1Points", player1Points );
//        outState.putInt("aiPoints", aiPoints);
//        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

//        roundCount = savedInstanceState.getInt("roundCount");
//        player1Points = savedInstanceState.getInt("player1Points");
//        aiPoints = savedInstanceState.getInt("aiPoints");
//        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}
