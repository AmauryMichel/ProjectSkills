package com.example.projectskills.group;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectskills.DBConnect;
import com.example.projectskills.R;
import com.example.projectskills.player.Player;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Vector;

public class GroupActivity extends AppCompatActivity {

    static Group group;
    final Vector<Player> listPlayer = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group);

        group = (Group) Objects.requireNonNull(getIntent().getSerializableExtra("group"));

        Runnable r = new Runnable() {
            @Override
            public void run() {
                JSONArray jsaMembers = DBConnect.getUsersGroup(String.valueOf(group.groupID)); // Store the result in the array
                JSONObject jso = new JSONObject();
                Player player = null;

                for (int i = 0; i < jsaMembers.length(); i++) {
                    try {
                        jso = jsaMembers.getJSONObject(i);
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
                    listPlayer.add(player);
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

        TextView tvMemberList = findViewById(R.id.tvGroupDetailMemberList);
        String members = "";

        for (Player p: listPlayer
             ) {
            members += p.username + "\n";
        }
        tvMemberList.setText(members);
        Log.d("MyApp", members);
    }

    public void toNewMember(View v){
        Intent intent = new Intent(this, NewMember.class);
        intent.putExtra("groupID", group.groupID);
        startActivityForResult(intent, 1);
    }

    @Override
    // Get the result from the activity
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) { //If the result is RESULT_OK, the member was successfully added
            finish(); //Restart the activity to update the member list
            startActivity(getIntent());
        }
    }
}
