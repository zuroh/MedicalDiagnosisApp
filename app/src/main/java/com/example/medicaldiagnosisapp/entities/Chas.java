package com.example.medicaldiagnosisapp.entities;

import com.google.android.gms.maps.model.LatLng;

/**
 * Chas is an entity class for the Chas Clinics markers
 * Contains its relevant information
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class Chas {

    private LatLng latLng;
    private String name;
    private String address;
    private String postalCode;

    /**
     * Constructor for the object Chas to construct an object array afterwards
     * @param lat
     * @param lng used to contains the long lat of the Chas Clinic
     * @param name used to contains the name of the Chas Clinic
     * @param address used to contains the address of the Chas Clinic
     * @param postalCode used to contains the postalCode of the Chas Clinic
     */
    public Chas(double lat, double lng, String name, String address, String postalCode) {
        this.latLng = new LatLng(lat, lng);
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
    }

    /**
     * The Getter method to get the address of Chas Clinic
     * @return address String of the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * The Getter method to get the name of Chas Clinic
     * @return name String of the name
     */
    public String getName() {
        return name;
    }

    /**
     * The Getter method to get the postal code of Chas Clinic
     * @return postalCode String of the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * The Getter method to get the LatLng of Chas Clinic
     * @return latLng LatLng of the location
     */
    public LatLng getLatLng() {
        return latLng;
    }

}
