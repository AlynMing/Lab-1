package com.example.lab1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.flash_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    findViewById(R.id.flash_answer).setVisibility(View.VISIBLE);
                    findViewById(R.id.flash_question).setVisibility(View.INVISIBLE);
                }});

        findViewById(R.id.flash_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    findViewById(R.id.flash_question).setVisibility(View.VISIBLE);
                    findViewById(R.id.flash_answer).setVisibility(View.INVISIBLE);
                }});
    }
}
