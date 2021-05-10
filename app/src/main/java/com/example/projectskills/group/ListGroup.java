package com.example.projectskills.group;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projectskills.DBConnect;
import com.example.projectskills.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

import static com.example.projectskills.MainActivity.userID;

public class ListGroup extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        final Vector<Group> listGroup = new Vector<Group>();

        super.onCreate(savedInstanceState);

        Log.d("MyApp", "toListGroup");
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Log.d("MyApp", "run");
                JSONArray jsaGroup = DBConnect.getGroups(userID); // Store the result in the array
                JSONObject jso = new JSONObject();
                Group group = null;
                Vector<Group> tempListGroup = new Vector<Group>();

                for (int i = 0; i < jsaGroup.length(); i++) {
                    Log.d("MyApp", "creating groups");
                    try {
                        jso = jsaGroup.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        Log.d("MyApp", "group create");
                        assert jso != null;
                        group = new Group(
                                jso.getInt("group_id"),
                                jso.getString("group_name"),
                                jso.getInt("is_manager") == 1); //Check if the value for is_manager is 1
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    listGroup.add(group);
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

        setContentView(R.layout.activity_list_group);
        RecyclerView rvDrill = findViewById(R.id.rvGroups);

        GroupAdapter adapter = new GroupAdapter(listGroup);
        rvDrill.setAdapter(adapter);
        rvDrill.setLayoutManager(new LinearLayoutManager(this));
    }
}
