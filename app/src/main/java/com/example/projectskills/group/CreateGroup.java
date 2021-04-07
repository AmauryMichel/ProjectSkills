package com.example.projectskills.group;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectskills.DBConnect;
import com.example.projectskills.MainActivity;
import com.example.projectskills.R;

public class CreateGroup extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(MainActivity.userID, "MYAPP");
        setContentView(R.layout.activity_cr_group);
    }

    public void onCreateGroup(View v) throws InterruptedException {
        EditText groupNameET = findViewById(R.id.newGroupName);
        // Get the text from the views
        // Set as final so that the thread can access them
        final String groupName = groupNameET.getText().toString();

        if (groupName.equals("")) { // Check if either field is empty
            Toast.makeText(this, "You must enter a group name", Toast.LENGTH_SHORT).show();
            return;
        }

        final String[] result = new String[1]; // String array to collect the result from the thread
        Runnable r = new Runnable() {
            @Override
            public void run() {
                result[0] = DBConnect.createGroup(groupName); // Store the result in the array
            };
        };

        Thread thr = new Thread(r);
        thr.start();
        thr.join();
        if (result[0].equals("Success")) { // Check the string
            Toast.makeText(this, "Group creation successful", Toast.LENGTH_SHORT).show();
            finish();
        } else { // If it wasn't successful, display the error message
            Toast.makeText(this, result[0], Toast.LENGTH_SHORT).show();
        }
    }

    public void close(View view) {
        finish();
    }
}
