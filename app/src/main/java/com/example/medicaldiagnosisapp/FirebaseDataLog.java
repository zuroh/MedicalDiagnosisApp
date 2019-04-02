package com.example.medicaldiagnosisapp;

import android.util.Log;
import android.os.AsyncTask;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import java.util.HashMap;
import java.util.Map;

public class FirebaseDataLog extends AsyncTask<DataLog, Void, Void> implements DBLoggingInterface {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference logRef;

    @Override
    protected Void doInBackground(DataLog... log){
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        logRef = mDatabaseRef.child("logs");

        updateDB(log[0]);

        return null;
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
