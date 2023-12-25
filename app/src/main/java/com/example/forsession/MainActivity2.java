package com.example.forsession;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView loginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        loginTextView = findViewById(R.id.HelloLogin);

        Intent intent = getIntent();

        String login = intent.getStringExtra("LOGIN");

        loginTextView.setText("Привет:" + "" + login);

        Button showUsersButton = findViewById(R.id.button);
        showUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper dbHelper = new MyDatabaseHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                String[] projection = {
                        MyDatabaseHelper.COLUMN_LOGIN,
                        MyDatabaseHelper.COLUMN_PASSWORD
                };

                Cursor cursor = db.query(
                        MyDatabaseHelper.TABLE_USERS,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null
                );

                while (cursor.moveToNext()) {
                    String login = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_LOGIN));
                    String password = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_PASSWORD));

                    Log.d("User", "Login: " + login + ", Password: " + password);
                }

                cursor.close();
                db.close();
            }
        });
    }
}