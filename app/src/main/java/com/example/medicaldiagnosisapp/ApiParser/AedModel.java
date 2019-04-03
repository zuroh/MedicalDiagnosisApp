package com.example.medicaldiagnosisapp.ApiParser;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AedModel {

    @SerializedName("Placemark")
    public ArrayList<aed> aeds;

    static public class aed {

        @SerializedName("name")
        public String name;

        @SerializedName("coordinates")
        public String coord;

    }

}