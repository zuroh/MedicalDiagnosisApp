package com.example.medicaldiagnosisapp.entities;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * DataLog is an entity class for data logging the diagnosis made by the users
 * Contains its relevant information
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
@IgnoreExtraProperties
public class DataLog {
    public String date;
    public double latitude;
    public double longitude;
    public String type;
    public String region;

    /**
     * Constructor for DataLog
     * also determines the region based on its coordinates
     * @param date
     * @param latitude
     * @param longitude
     * @param type
     */
    public DataLog (String date,double latitude,double longitude,String type){
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;

        if(longitude<=103.752){
            region = "West";
        }
        else if(longitude>=103.897){
            region = "East";
        }
        else{
            if(latitude<=1.3405){
                region = "South";
            }
            else{
                region = "North";
            }
        }
    }

    /**
     * @return result HashMap that maps the DataLog parameters to each respective String counterparts
     */
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("date", date);
        result.put("latitude", latitude);
        result.put("longitude", longitude);
        result.put("type", type);
        result.put("region", region);

        return result;
    }
  
}