package com.example.projectskills;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameET, passwordET;

    RadioButton player, manager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameET = findViewById(R.id.newUsername);
        passwordET = findViewById(R.id.newPassword);

        player = findViewById((R.id.radioPlayer));
        manager = findViewById((R.id.radioManager));
    }

    public void onCancel(View view)
    {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    public void onRegister(View view)
    {
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        String accType = "";

        if(player.isChecked())
        {
            accType = "player";
        }
        else if(manager.isChecked())
        {
            accType = "manager";
        }

        String type = "register";

        DBConnect connection = new DBConnect(this);
        connection.execute(type, username, password, accType);
    }
}