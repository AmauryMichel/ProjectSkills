package com.example.projectskills;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*
    Variable to know if the user is logged in
    Temporary measure
     */
    public static boolean isLoggedIn = false;
    public static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isLoggedIn){ //Check if the user is already logged in
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_logged_out);
        }
    }

    public void toLogin(View v){ // Create a Login activity
        Intent intent = new Intent(this, Login.class);
        startActivityForResult(intent, 1);
    }

    public void toRegister(View v) { // Create a Register activity
        Intent intent = new Intent(this, Register.class);
        startActivityForResult(intent, 1);
    }

    @Override
    // Get the result from the activity
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){ //If the result is RESULT_OK, the registration or log in was successful
            isLoggedIn = true;
            assert data != null;
            username = data.getStringExtra("username"); // Store the username
            // Restart the activity to have the correct layout
            finish();
            startActivity(getIntent());
        }
    }
}