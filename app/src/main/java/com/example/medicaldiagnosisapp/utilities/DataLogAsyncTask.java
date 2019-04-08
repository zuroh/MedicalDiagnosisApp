package com.example.medicaldiagnosisapp.utilities;

import android.os.AsyncTask;

import com.example.medicaldiagnosisapp.entities.DataLog;
import com.example.medicaldiagnosisapp.entities.DataLogContext;

/**
 * DataLogAsyncTask starts the reading of resources in the background
 * the moment the application begins. Makes data logging happen faster
 * when required by the application.
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class DataLogAsyncTask extends AsyncTask<DataLog, Void, Void> {

    /**
     * Part of AsynTask implementation
     * Instantiates an instance of firebase logging
     * @param log Entity DataLog is parsed
     * @return
     */
    @Override
    protected Void doInBackground(DataLog... log){
        DataLogContext context = new DataLogContext(new FirebaseDataLog());
        context.logData(log[0]);

        return null;
    }

}
