package com.example.medicaldiagnosisapp.utilities;

import android.util.Log;

import com.example.medicaldiagnosisapp.entities.DataLog;
import com.example.medicaldiagnosisapp.interfaces.DBLoggingInterface;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import java.util.HashMap;
import java.util.Map;

/**
 * FirebaseDataLog implements the DBLoggingInterface inorder for the application
 * to perform data logging. An alternate class like MySQLDataLog could have implemented DBLoggingInterface
 * showing loose coupling
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class FirebaseDataLog implements DBLoggingInterface {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference logRef;

    /**
     * Get instance of database
     * Get child nodes referred by data logging
     */
    public FirebaseDataLog(){
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        logRef = mDatabaseRef.child("logs");
    }

    /**
     * Updates the information to the online google irebase
     * @param log DataLog entity containing all the information that is to be stored for admin use
     */
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
