package com.example.medicaldiagnosisapp.utilities;

import android.os.AsyncTask;

import com.example.medicaldiagnosisapp.entities.DataLog;
import com.example.medicaldiagnosisapp.entities.DataLogContext;

public class DataLogAsyncTask extends AsyncTask<DataLog, Void, Void> {
    @Override
    protected Void doInBackground(DataLog... log){
        DataLogContext context = new DataLogContext(new FirebaseDataLog());
        context.logData(log[0]);

        return null;
    }

}
