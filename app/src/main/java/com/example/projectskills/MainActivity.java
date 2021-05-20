package com.example.projectskills;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectskills.drills.CreateDrill;
import com.example.projectskills.drills.Drill;
import com.example.projectskills.group.CreateGroup;
import com.example.projectskills.group.ListGroup;
import com.example.projectskills.drills.*;

public class MainActivity extends AppCompatActivity {

    /*
    Variable to know if the user is logged in
    Temporary measure
     */
    public static boolean isLoggedIn = false;
    public static String username;
    public static String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isLoggedIn) { //Check if the user is already logged in
            setContentView(R.layout.activity_main);
            TextView tvWelcome = findViewById(R.id.welcome);
            tvWelcome.setText("Welcome " + username);
        } else {
            setContentView(R.layout.activity_logged_out);
        }
    }

    public void toLogin(View v) { // Create a Login activity
        Intent intent = new Intent(this, Login.class);
        startActivityForResult(intent, 1);
    }

    public void toRegister(View v) { // Create a Register activity
        Intent intent = new Intent(this, Register.class);
        startActivityForResult(intent, 1);
    }

    public void toCreateGroup(View v) {
        Intent intent = new Intent(this, CreateGroup.class);
        startActivity(intent);
    }

    public void toListGroup(View v) {
        Intent intent = new Intent(this, ListGroup.class);
        startActivity(intent);
    }

    public void toCreateDrill(View v){
        Intent intent = new Intent(this, CreateDrill.class);
        startActivity(intent);
    }

    public void toListDrill(View v) {
        Intent intent = new Intent(this, ListDrill.class);
        startActivity(intent);
    }

    @Override
    // Get the result from the activity
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) { //If the result is RESULT_OK, the registration or log in was successful
            isLoggedIn = true;
            assert data != null;
            username = data.getStringExtra("username"); // Store the username
            userID = (data.getStringExtra("userID"));
            // Restart the activity to have the correct layout
            finish();
            startActivity(getIntent());
        }
    }
}