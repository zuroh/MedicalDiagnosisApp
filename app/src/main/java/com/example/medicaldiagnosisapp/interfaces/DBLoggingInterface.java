package com.example.medicaldiagnosisapp.interfaces;

import com.example.medicaldiagnosisapp.entities.DataLog;

/**
 * DBLoggingInterface is an interface for updating the database as
 * only the relevant information in the DataLog entity is concerned
 * when data logging
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public interface DBLoggingInterface {

    /**
     * Abstract method for FireBaseDataLog to implement
     * @param log used to contain required data logging information
     */
    void updateDB(DataLog log);
}
