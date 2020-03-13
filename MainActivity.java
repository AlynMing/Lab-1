package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    flashDB flashDB;
    List<flash> allCards;
    int currentCard = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashDB = new flashDB(getApplicationContext());
        allCards = flashDB.getAllCards();

        if (allCards != null && allCards.size() > 0) {
            ((TextView) findViewById(R.id.flash_question)).setText(allCards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flash_answer)).setText(allCards.get(0).getAnswer());
        }

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
                tent.putExtra("oldq","");
                tent.putExtra("olda","");
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
                tent.putExtra("oldq",editQ);
                tent.putExtra("olda",editA);
                setResult(RESULT_OK, tent);
                MainActivity.this.startActivityForResult(tent, 200);
            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lastCard = currentCard;
                while (lastCard==currentCard && currentCard!=allCards.size()) {
                    currentCard = getRandom(0, allCards.size());
                }

                if (currentCard > allCards.size() - 1) {
                    currentCard = 0;
                }
                if (allCards.size()>0) {
                    ((TextView) findViewById(R.id.flash_question)).setText(allCards.get(currentCard).getQuestion());
                    findViewById(R.id.flash_question).setVisibility(View.VISIBLE);
                    ((TextView) findViewById(R.id.flash_answer)).setText(allCards.get(currentCard).getAnswer());
                    findViewById(R.id.flash_answer).setVisibility(View.INVISIBLE);
            }}
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashDB.deleteCard(((TextView) findViewById(R.id.flash_question)).getText().toString());
                allCards = flashDB.getAllCards();
                if (currentCard!=0 && allCards.size()>0) {
                    currentCard--;
                    ((TextView) findViewById(R.id.flash_question)).setText(allCards.get(currentCard).getQuestion());
                    findViewById(R.id.flash_question).setVisibility(View.VISIBLE);
                    ((TextView) findViewById(R.id.flash_answer)).setText(allCards.get(currentCard).getAnswer());
                    findViewById(R.id.flash_answer).setVisibility(View.INVISIBLE);
                }
                else {
                    ((TextView) findViewById(R.id.flash_question)).setText(getString(R.string.emptyQ));
                    findViewById(R.id.flash_question).setVisibility(View.VISIBLE);
                    ((TextView) findViewById(R.id.flash_answer)).setText(getString(R.string.emptyA));
                    findViewById(R.id.flash_answer).setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public int getRandom(int minNum, int maxNum) {
        Random random = new Random();
        return random.nextInt((maxNum-minNum)+1) +minNum;
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
                flashDB.insertCard(new flash(newQ, newA));
                allCards = flashDB.getAllCards();
            }}
        else if (requestCode==200){
            if (data!=null){
                String newQ = data.getExtras().getString("newQuestion");
                TextView question = findViewById(R.id.flash_question);
                question.setText(newQ);
                String newA = data.getExtras().getString("newAnswer");
                TextView answer = findViewById(R.id.flash_answer);
                answer.setText(newA);
                String oldq = data.getExtras().getString("oldq");
                String olda = data.getExtras().getString("olda");
                flashDB.deleteCard(oldq);
                flashDB.insertCard(new flash(newQ, newA));
                allCards = flashDB.getAllCards();
        }}
    }


}
