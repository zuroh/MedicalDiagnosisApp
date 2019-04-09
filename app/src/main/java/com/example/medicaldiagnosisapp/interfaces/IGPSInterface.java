package com.example.medicaldiagnosisapp.interfaces;

/**
 * IGPSInterface is an interface for finding the current location as
 * only longitude and latitude is concerned
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public interface IGPSInterface {

    /**
     * Abstract method for locationChanged to implement
     * @param longitude
     * @param latitude used to contains the long lat of the current location
     */
    void locationChanged(double longitude, double latitude);
}
