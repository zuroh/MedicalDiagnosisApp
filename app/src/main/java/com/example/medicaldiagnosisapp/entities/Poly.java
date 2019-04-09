package com.example.medicaldiagnosisapp.entities;

import com.google.android.gms.maps.model.LatLng;

/**
 * Poly is an entity class for the Polyclinic markers
 * Contains its relevant information
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class Poly {

    private LatLng latLng;
    private String name;
    private String address;

    /**
     * Constructor for the object Poly to construct an object array afterwards
     * @param lat
     * @param lng used to contains the long lat of the Polyclinic
     * @param name used to contains the name of the Polyclinic
     * @param address used to contains the address of the Polyclinic
     */
    public Poly(double lat, double lng, String name, String address) {
        this.latLng = new LatLng(lat, lng);
        this.name = name;
        this.address = address;
    }

    /**
     * The Getter method to get the address of Polyclinic
     * @return address String of the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * The Getter method to get the name of Polyclinic
     * @return name String of the name
     */
    public String getName() {
        return name;
    }

    /**
     * The Getter method to get the LatLng of Polyclinic
     * @return latLng LatLng of the location
     */
    public LatLng getLatLng() {
        return latLng;
    }

}
