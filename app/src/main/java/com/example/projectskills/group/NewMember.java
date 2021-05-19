package com.example.projectskills.group;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectskills.DBConnect;
import com.example.projectskills.R;
import com.example.projectskills.player.Player;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewMember extends AppCompatActivity {

    static int groupID;
    final static ArrayList<Player> alNotMembers = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_invite_member);

        groupID = getIntent().getIntExtra("groupID", 0);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                JSONArray jsaNotMembers = DBConnect.getUsersNotGroup(String.valueOf(groupID)); // Store the result in the array
                JSONObject jso = new JSONObject();
                Player player = null;

                for (int i = 0; i < jsaNotMembers.length(); i++) {
                    try {
                        jso = jsaNotMembers.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        assert jso != null;
                        player = new Player(
                                jso.getInt("player_id"),
                                jso.getString("username"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    alNotMembers.add(player);
                }
            }
        };

        Thread thr = new Thread(r);
        thr.start();
        try {
            thr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayAdapter<Player> adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alNotMembers);

        Spinner spinMember = findViewById(R.id.spinMembers);
        spinMember.setAdapter(adp);
    }

    static String result;

    public void addMember(View v) throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Spinner spinner = findViewById(R.id.spinMembers);
                Player p = (Player) spinner.getSelectedItem();
                result = DBConnect.addMember(String.valueOf(p.userID), String.valueOf(groupID)); // Store the result in the array
            }
        };

        Thread thr = new Thread(r);
        thr.start();
        thr.join();
        if (result.equals("Success")) { // Check the string
            Toast.makeText(this, "Member successfully added", Toast.LENGTH_SHORT).show();
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        } else { // If it wasn't successful, display the error message
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }
}
