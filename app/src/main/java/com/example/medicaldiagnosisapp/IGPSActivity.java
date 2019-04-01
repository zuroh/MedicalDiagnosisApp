package com.example.medicaldiagnosisapp;

public interface IGPSActivity {

    /**
     * Abstract method for locationChanged to implement
     * @param longitude
     * @param latitude used to contains the long lat of the current location
     */
    void locationChanged(double longitude, double latitude);
}
