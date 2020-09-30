package com.rku.demojsonbaseadapter;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ListView lstData;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstData = findViewById(R.id.lstData);
        adapter = new CustomAdapter(this,generateData());
        lstData.setAdapter(adapter);
    }

    private JSONArray generateData() {
        JSONObject jsonObject;
        JSONArray jsonArray = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = 0;
            size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer,"UTF-8");
            Log.i("json",json);

            jsonObject = new JSONObject(json);
            jsonArray = jsonObject.getJSONArray("sites");

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}