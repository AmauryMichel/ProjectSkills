package com.example.projectskills.drills;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projectskills.DBConnect;
import com.example.projectskills.R;
import com.example.projectskills.group.Group;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

import static com.example.projectskills.MainActivity.userID;

public class ListDrill extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        final Vector<Drill> listDrill = new Vector<Drill>();

        super.onCreate(savedInstanceState);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                JSONArray jsaGroup = DBConnect.getDrills(userID); // Store the result in the array
                JSONObject jso = new JSONObject();
                Drill drill = null;

                for (int i = 0; i < jsaGroup.length(); i++) {
                    try {
                        jso = jsaGroup.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        assert jso != null;
                        drill = new Drill(jso.getInt("id"),
                                jso.getString("drill_name"),
                                jso.getString("drill_desc"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    listDrill.add(drill);
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

        setContentView(R.layout.activity_list_drill);
        RecyclerView rvDrill = findViewById(R.id.rvDrills);

        DrillAdapter adapter = new DrillAdapter(listDrill);
        rvDrill.setAdapter(adapter);
        rvDrill.setLayoutManager(new LinearLayoutManager(this));
    }
}
