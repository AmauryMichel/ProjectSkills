package com.example.projectskills;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
    }

    public void onLogin(View v) throws InterruptedException {
        // Get the views
        EditText userView = findViewById(R.id.username);
        EditText passView = findViewById(R.id.password);
        // Get the text from the views
        // Set as final so that the thread can access them
        final String username = userView.getText().toString();
        final String password = passView.getText().toString();

        if (username.equals("") || password.equals("")) { // Check if either field is empty
            Toast.makeText(this, "You must enter both your username and your password", Toast.LENGTH_SHORT).show();
            return;
        }

        final String[] result = new String[1]; // String array to collect the result from the thread
        Runnable r = new Runnable() {
            @Override
            public void run() {
                result[0] = DBConnect.login(username, password); // Store the result in the array
            };
        };

        Thread thr = new Thread(r);
        thr.start();
        thr.join();
        if (result[0].startsWith("Success")) { // Check the string
            Toast.makeText(this, "Connection successful", Toast.LENGTH_SHORT).show();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("username", username);
            returnIntent.putExtra("userID", result[0].substring(7)); // Get the ID
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        } else { // If it wasn't successful, display the error message
            Toast.makeText(this, result[0], Toast.LENGTH_SHORT).show();
        }
    }

    public void close(View view) {
        finish();
    }
}
