package com.example.projectskills.drills;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projectskills.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class ListDrill extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Vector<Drill> listDrill = new Vector<Drill>();

        super.onCreate(savedInstanceState);

        // Placeholder to test how they display
        JSONObject test = new JSONObject();
        try {
            test.put("drillId", 1);
            test.put("drillName","Activity name");
            test.put("drillDesc","Activity description");
            test.put("groupName","Group name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject test2 = new JSONObject();
        try {
            test2.put("drillId", 2);
            test2.put("drillName","test1");
            test2.put("drillDesc","test12");
            test2.put("groupName","test13");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject test3 = test2;

        JSONArray jsaDrills = new JSONArray();
        jsaDrills.put(test);
        jsaDrills.put(test2);
        jsaDrills.put(test3);
        JSONObject jso = new JSONObject();
        Drill dri = null;
        for (int i = 0; i < jsaDrills.length(); i++) {
            try {
                jso = jsaDrills.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                Log.d("MyApp", "drill create");
                assert jso != null;
                dri = new Drill(
                        jso.getInt("drillId"),
                        jso.getString("drillName"),
                        jso.getString("drillDesc"),
                        jso.getString("groupName"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listDrill.add(dri);
        }

        setContentView(R.layout.activity_list_drill);
        RecyclerView rvDrill = findViewById(R.id.rvDrills);

        DrillAdapter adapter = new DrillAdapter(listDrill);
        rvDrill.setAdapter(adapter);
        rvDrill.setLayoutManager(new LinearLayoutManager(this));
    }
}
