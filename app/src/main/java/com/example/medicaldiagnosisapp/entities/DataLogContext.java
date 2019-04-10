package com.example.medicaldiagnosisapp.entities;

import com.example.medicaldiagnosisapp.interfaces.DBLoggingInterface;

/**
 * DataLogContext implements the DBLoggingInterface and
 * is a class for the DataLog entity to get its context
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class DataLogContext {
    private DBLoggingInterface logger;

    /**
     * The Getter method to get the context of the DataLog
     * @return latLng LatLng of the location
     */
    public DataLogContext(DBLoggingInterface logger){
        this.logger = logger;
    }

    /**
     * Updates the firebase with the information in log
     * @param log used to contain required data logging information
     */
    public void logData(DataLog log){
        logger.updateDB(log);
    }

}
