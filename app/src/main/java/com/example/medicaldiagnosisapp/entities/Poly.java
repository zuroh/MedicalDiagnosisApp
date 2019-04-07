package com.example.medicaldiagnosisapp.entities;

import com.google.android.gms.maps.model.LatLng;
public class Poly {

    private LatLng latLng;
    private String name;
    private String address;
    private String postalCode;

    public Poly(double lat, double lng, String name, String address) {
        this.latLng = new LatLng(lat, lng);
        this.name = name;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

}
