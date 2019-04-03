package com.example.medicaldiagnosisapp.entity;

import com.google.android.gms.maps.model.LatLng;
public class Poly {

    private LatLng latLng;
    private String name;
    private String address;
    private String postalCode;

    public Poly(double lat, double lng, String name, String address, String postalCode) {
        this.latLng = new LatLng(lat, lng);
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public LatLng getLatLng() {
        return latLng;
    }

}
