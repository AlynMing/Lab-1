package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        if (getIntent()!=null) {
            String oldQ = getIntent().getStringExtra("newQuestion");
            EditText question = findViewById(R.id.newQuestion);
            question.setText(oldQ);
            String oldA = getIntent().getStringExtra("newAnswer");
            EditText answer = findViewById(R.id.newAnswer);
            answer.setText(oldA);
        }

        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qstr = ((EditText) findViewById(R.id.newQuestion)).getText().toString();
                String astr = ((EditText) findViewById(R.id.newAnswer)).getText().toString();
                if (qstr.compareTo("")!=0 && astr.compareTo("")!=0) {
                    Intent data = new Intent();
                    data.putExtra("newQuestion", qstr);
                    data.putExtra("newAnswer", astr);
                    setResult(RESULT_OK, data);
                    finish();
                }
                else {
                    Toast bread = Toast.makeText(getApplicationContext(), "Field empty. Fill both.", Toast.LENGTH_SHORT);
                    bread.setGravity(Gravity.BOTTOM, 0, 0);
                    bread.show();
                }
            }
        });
    }
}
