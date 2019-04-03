package com.example.medicaldiagnosisapp;

import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import java.util.HashMap;
import java.util.Map;

public class FirebaseDataLog implements DBLoggingInterface {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference logRef;

    public FirebaseDataLog(){
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        logRef = mDatabaseRef.child("logs");
    }

    @Override
    public void updateDB(DataLog log){

        try {
            DatabaseReference newLogRef = logRef.push();
            String logKey = newLogRef.getKey();
            Map<String, Object> logValues = log.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put(logKey, logValues);
            logRef.updateChildren(childUpdates);
        } catch (Exception e) {
            Log.e("Firebase Update", "Exception: " + e.getMessage());
        }
    }
}