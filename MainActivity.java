package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
            }
        });

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tent = new Intent(MainActivity.this, AddActivity.class);
                MainActivity.this.startActivityForResult(tent, 100);
            }
        });

        findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tent = new Intent(MainActivity.this, AddActivity.class);
                TextView flashQ = findViewById(R.id.flash_question);
                TextView flashA = findViewById(R.id.flash_answer);
                String editQ = flashQ.getText().toString();
                String editA = flashA.getText().toString();
                tent.putExtra("newQuestion", editQ);
                tent.putExtra("newAnswer", editA);
                setResult(RESULT_OK, tent);
                MainActivity.this.startActivityForResult(tent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (data!=null){
                String newQ = data.getExtras().getString("newQuestion");
                TextView question = findViewById(R.id.flash_question);
                question.setText(newQ);
                String newA = data.getExtras().getString("newAnswer");
                TextView answer = findViewById(R.id.flash_answer);
                answer.setText(newA);
        }}
    }
}
