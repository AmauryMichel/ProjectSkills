package com.example.projectskills;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameET, passwordET;

    private RadioButton player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameET = findViewById(R.id.newUsername);
        passwordET = findViewById(R.id.newPassword);

        player = findViewById((R.id.radioPlayer));
    }

    public void close(View view) {
        finish();
    }

    public void onRegister(View view) {
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        String accType = "";

        if (username.equals("") || password.equals("")) { // Check if the
            Toast.makeText(this, "You must enter both your username and your password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (player.isChecked()) {
            accType = "player";
        } else { // It's either player or manager
            accType = "manager";
        }

        String type = "register";

        DBConnect connection = new DBConnect(this);
        connection.execute(type, username, password, accType);
    }
}