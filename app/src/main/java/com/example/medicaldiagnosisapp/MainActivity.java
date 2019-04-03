package com.example.medicaldiagnosisapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ChildEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView funView;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference funFactsRef;
    private JSONObject funFactJSON;
    private Map<String, Integer> funFactTracker;

    private Timer mTimer;
    private TimerTask mTimerTask;
    private Handler mHandler = new Handler();

    private int conditionId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        funView = findViewById(R.id.funView);
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        funFactsRef = mDatabaseRef.child("funfacts");
        funFactTracker = new HashMap<String, Integer>();
        fillTracker();

        String dataGovURL = "https://data.gov.sg/api/action/datastore_search?";
        String funFactURL = dataGovURL + "resource_id=d40e7d15-cd97-4aa2-922d-403248b6bb33&q=%7B%22year%22%3A%222014%22%7D";
        try {
            funFactJSON = new WebAPIHandler().execute(funFactURL).get();
        } catch (Exception e) {
            Log.e("Data.gov.sg", "Exception: " + e.getMessage());
        }

        startTimer();

        //Common Conditions Buttons nav Start//

        Button btnHeartAttk = (Button)findViewById(R.id.btnHeartAttack);
        Button btnStroke = (Button)findViewById(R.id.btnStroke);
        Button btnHeatEx = (Button)findViewById(R.id.btnHeatExhaustion);
        Button btnMjrTrauma = (Button)findViewById(R.id.btnTrauma);

        btnHeartAttk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent goToHeartAttackInfo = new Intent(getApplicationContext(), HeartAttackActivity.class);
                startActivity(goToHeartAttackInfo);
            }
        });

        btnStroke.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent goToStrokeInfo = new Intent(getApplicationContext(), StrokeActivity.class);
                startActivity(goToStrokeInfo);
            }
        });

        btnHeatEx.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent goToHeatExhaustionInfo = new Intent(getApplicationContext(), HeatActivity.class);
                startActivity(goToHeatExhaustionInfo);
            }
        });

        btnMjrTrauma.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent goToMajorTraumaInfo = new Intent(getApplicationContext(), MajorTraumaActivity.class);
                startActivity(goToMajorTraumaInfo);
            }
        });

        //Common Conditions Buttons nav End//

        //Bottom Navigation Start//

        BottomNavigationView btmNavMenu = (BottomNavigationView) findViewById(R.id.btm_navigation_menu);

        btmNavMenu.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_action_info:
                                Intent moveToInfo = new Intent(getApplicationContext(), InformationActivity.class);
                                startActivity(moveToInfo);
                                break;
                            case R.id.nav_action_call:
                                Intent moveToContact = new Intent(getApplicationContext(), ContactActivity.class);
                                startActivity(moveToContact);
                                break;
                            case R.id.nav_action_diagnose:
                                Intent moveToDiagnose1 = new Intent(getApplicationContext(), DiagnoseActivityPage1.class);
                                startActivity(moveToDiagnose1);
                                break;
                            case R.id.nav_action_locate:
                                Intent moveToLocate = new Intent(getApplicationContext(), LocateActivity.class);
                                startActivity(moveToLocate);
                                break;
                            case R.id.nav_action_help:
                                Intent moveToHelp = new Intent(getApplicationContext(), HelpActivity.class);
                                startActivity(moveToHelp);
                                break;
                        }
                        return true;
                    }

                });

        //Bottom navigation end//

    }

    public void startTimer(){

        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        String conditionString = "";
                        try {
                            JSONObject condition = funFactJSON.getJSONObject("result").getJSONArray("records").getJSONObject(conditionId);
                            conditionString = condition.getString("disease_condition");
                        } catch (Exception e) {
                            Log.e("JSON", "Exception: " + e.getMessage());
                        }

                        displayFunFact(conditionString, funFactTracker.get(conditionString));
                        conditionId++;
                        conditionId %= 10;
                    }
                });
            }
        };

        mTimer.schedule(mTimerTask, 1, 30000);
    }

    public void fillTracker(){
        int initValue = 1;
        funFactTracker.put("Cancer", initValue);
        funFactTracker.put("Cancer count", 1);
        funFactTracker.put("Ischaemic heart diseases", initValue);
        funFactTracker.put("Ischaemic heart diseases count", 3);
        funFactTracker.put("Intestinal infectious diseases", initValue);
        funFactTracker.put("Intestinal infectious diseases count", 5);
        funFactTracker.put("Pneumonia", initValue);
        funFactTracker.put("Pneumonia count", 1);
        funFactTracker.put("Other heart diseases", initValue);
        funFactTracker.put("Other heart diseases count", 3);
        funFactTracker.put("Infections of skin & subcutaneous tissue", initValue);
        funFactTracker.put("Infections of skin & subcutaneous tissue count", 3);
        funFactTracker.put("Accident, poisoning and violence", initValue);
        funFactTracker.put("Accident, poisoning and violence count", 10);
        funFactTracker.put("Obstetric complications affecting fetus and newborn", initValue);
        funFactTracker.put("Obstetric complications affecting fetus and newborn count", 1);
        funFactTracker.put("Diabetes mellitus", initValue);
        funFactTracker.put("Diabetes mellitus count", 3);
        funFactTracker.put("Cerebrovascular diseases (Including stroke)", initValue);
        funFactTracker.put("Cerebrovascular diseases (Including stroke) count", 5);
    }

    public void displayFunFact(String tag, int id){
        final int funFactId = id;

        Query query = funFactsRef.orderByChild("tag").equalTo(tag).limitToFirst(funFactId);
        query.addChildEventListener(new ChildEventListener() {
            int funFactCount = 1;
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                if(funFactCount==funFactId){
                    String s = (String) dataSnapshot.child("text").getValue();
                    funView.setText(s);
                } else if(funFactCount<funFactId){
                    funFactCount++;
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        int funFactCurrent = funFactTracker.get(tag+" count");
        int newId = (id%funFactCurrent) + 1;
        funFactTracker.put(tag, newId);
    }

}
