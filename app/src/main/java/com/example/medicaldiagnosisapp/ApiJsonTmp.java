package com.example.medicaldiagnosisapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;


public class ApiJsonTmp extends AppCompatActivity {

    ArrayList<String> genderList = new ArrayList<>();
    String queryUrl = "https://data.gov.sg/api/action/datastore_search?resource_id=3d51e1e1-4069-4e04-85d5-ae3a310e98d4&limit=5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_json_tmp);

        final TextView textView = (TextView) findViewById(R.id.data_gov_list);
// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, queryUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        textView.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

/*        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, queryUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray records = response.getJSONArray("records");
                            for (int i=0; i<records.length(); i++) {
                                JSONObject indiRecord = records.getJSONObject(i);
                                genderList.add(indiRecord.getString("gender"));
                                tmp1 += " " + indiRecord.getString("gender");
                            }
                            textView.setText("Genders: " + tmp1);
                        }   catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });*/

// Access the RequestQueue through your singleton class.

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
}
