package com.example.forsession;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView loginTextVie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        loginTextVie = findViewById(R.id.HelloLogin);

        Intent intent = getIntent();

        String login = intent.getStringExtra("LOGIN");

        loginTextVie.setText("Приветсвтвую:" + "" + login);
    }
}