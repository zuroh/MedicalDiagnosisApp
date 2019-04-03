package com.example.medicaldiagnosisapp;

import android.os.AsyncTask;

public class DataLogTask extends AsyncTask<DataLog, Void, Void> {
    @Override
    protected Void doInBackground(DataLog... log){
        DataLogContext context = new DataLogContext(new FirebaseDataLog());
        context.logData(log[0]);

        return null;
    }

}
