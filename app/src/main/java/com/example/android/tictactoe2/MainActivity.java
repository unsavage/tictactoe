package com.example.android.tictactoe2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    public static String game_type = "standard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void initialize() {
        Button solo = findViewById(R.id.solo_button);
        Button multi = findViewById(R.id.multi_button);
        RadioButton standard = findViewById(R.id.standard);
        RadioButton advanced = findViewById(R.id.advanced);


        solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity_gamescreen = new Intent(MainActivity.this, gamescreen.class);
                activity_gamescreen.putExtra("game_type", MainActivity.game_type);
                startActivity(activity_gamescreen);
            }
        });

        multi.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent activity_gamescreen = new Intent(MainActivity.this, gamescreen.class);
                activity_gamescreen.putExtra("game_type", MainActivity.game_type);
                startActivity(activity_gamescreen);
            }
        });

        standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.game_type = "standard";
            }
        });

        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.game_type = "advanced";
            }
        });
    }


}
