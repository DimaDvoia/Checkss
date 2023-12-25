package com.example.forsession;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText loginEditText;
    private EditText passwordEditText;
     private Button loginButton;
    private MyDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEditText = findViewById(R.id.stringLOGIN);
        passwordEditText = findViewById(R.id.stringPassword);
        loginButton = findViewById(R.id.buttonLogin);

        databaseHelper = new MyDatabaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Проверка наличия логина и пароля
                if (!TextUtils.isEmpty(login) && !TextUtils.isEmpty(password)) {
                    // Добавление нового пользователя в базу данных
                    addUserToDatabase(login, password);
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("LOGIN", login);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Введите логин и пароль", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addUserToDatabase(String login, String password) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.COLUMN_LOGIN, login);
        values.put(MyDatabaseHelper.COLUMN_PASSWORD, password);

        long newRowId = db.insert(MyDatabaseHelper.TABLE_USERS, null, values);

        if (newRowId != -1) {
            Toast.makeText(MainActivity.this, "Пользователь добавлен", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Ошибка при добавлении пользователя", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}