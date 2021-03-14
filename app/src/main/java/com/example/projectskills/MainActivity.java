package com.example.projectskills;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText usernameET, passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameET = findViewById(R.id.username);
        passwordET = findViewById(R.id.password);
    }

    public void onLogin(View view) {
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        String type = "login";

        if (username.equals("") || password.equals("")) { //If either field is empty
            Toast.makeText(this, "You must enter both your username and your password", Toast.LENGTH_SHORT).show();
            return;
        }

        DBConnect connection = new DBConnect(this);
        connection.execute(type, username, password);
    }

    public void onRegister(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

}