package com.example.medicaldiagnosisapp.entities;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class DataLog {
    public String date;
    public double latitude;
    public double longitude;
    public String type;
    public String region;

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