package com.example.medicaldiagnosisapp;

public class DataLog {
    public String date;
    public double latitude;
    public double longitude;
    public String type;

    public DataLog (String date,double latitude,double longitude,String type){
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }
}
