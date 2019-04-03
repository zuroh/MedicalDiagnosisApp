package com.example.medicaldiagnosisapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;

public class AdminActivityFinal extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference logRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_final);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        logRef = mDatabaseRef.child("logs");

        Bundle extras = getIntent().getExtras();
        final HashMap<String, Object> adminView = (HashMap<String, Object>) extras.get("adminView");
        String[] startEndDate = (String[]) adminView.get("Start/End Date");
        final ArrayList<String> condition = (ArrayList<String>) adminView.get("Conditions");
        final ArrayList<String> regions = (ArrayList<String>) adminView.get("Regions");

        Query query = logRef.orderByChild("date").startAt(startEndDate[0]).endAt(startEndDate[1]);

        Log.d("tag", "entering query" + startEndDate[0] + startEndDate[1]);

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                String region = (String) dataSnapshot.child("region").getValue();
                String type = (String) dataSnapshot.child("type").getValue();

                regionsearch:
                for(String regionChosen : regions){
                    Log.d("tag", "reg find"+region+regionChosen);
                    if(region.equals(regionChosen)){
                        Log.d("tag", "region same");
                        for(String conditionChosen : condition){
                            Log.d("tag", "Confind");
                            if(type.equals(conditionChosen)){
                                //get coordinates
                                double latitude = (double) dataSnapshot.child("latitude").getValue();
                                double longitude = (double) dataSnapshot.child("longitude").getValue();
                                //add marker on map

                                Toast t = Toast.makeText(getApplicationContext(), String.valueOf(latitude), Toast.LENGTH_LONG);
                                Log.d("tag", "Confound");
                                t.show();
                                break regionsearch;
                            }
                        }
                    }
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

        //Bottom Toolbar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_admin_exit:
                                Intent moveToMainAct = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(moveToMainAct);
                                break;
                            case R.id.action_admin_restart:
                                Intent moveToAdminAct1 = new Intent(getApplicationContext(),AdminActivity1.class);
                                startActivity(moveToAdminAct1);
                                break;
                        }
                        return true;
                    }
                });
        //Bottom Toolbar end

        //Gmap View

        //Gmap View end

        //Dynamic textview

        //Dynamic textview end
    }
}
