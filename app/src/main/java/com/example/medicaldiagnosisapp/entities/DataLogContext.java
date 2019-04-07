package com.example.medicaldiagnosisapp.entities;

import com.example.medicaldiagnosisapp.interfaces.DBLoggingInterface;

public class DataLogContext {
    private DBLoggingInterface logger;

    public DataLogContext(DBLoggingInterface logger){
        this.logger = logger;
    }

    public void logData(DataLog log){
        logger.updateDB(log);
    }

}
