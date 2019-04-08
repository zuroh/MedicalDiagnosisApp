package com.example.medicaldiagnosisapp.entities;

import com.google.android.gms.maps.model.LatLng;

/**
 * Aed is an entity class for the Aed markers
 * Contains its relevant information
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class Aed {

    private LatLng latLng;
    private String name;
    private String address;
    private String postalCode;

    /**
     * Constructor for the object Aed to construct an object array afterwards
     * @param lat
     * @param lng used to contains the long lat of the Aed
     * @param name used to contains the name of the Aed
     * @param address used to contains the address of the Aed
     * @param postalCode used to contains the postalCode of the Aed
     */
    public Aed(double lat, double lng, String name, String address, String postalCode) {
        this.latLng = new LatLng(lat, lng);
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
    }

    /**
     * The Getter method to get the address of Aed
     * @return address String of the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * The Getter method to get the name of Aed
     * @return name String of the name
     */
    public String getName() {
        return name;
    }

    /**
     * The Getter method to get the postal code of Aed
     * @return postalCode String of the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * The Getter method to get the LatLng of Aed
     * @return latLng LatLng of the location
     */
    public LatLng getLatLng() {
        return latLng;
    }

}
