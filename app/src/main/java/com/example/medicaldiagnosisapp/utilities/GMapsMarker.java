package com.example.medicaldiagnosisapp.utilities;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GMapsMarker {

    /**
     * For general usage, using the actual code is actually more meaningful..
     * Usage example
     * GMapsMarker.createMarker(mMap,
     *  new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
     *  "Current Location",
     *  "You are currently " + String.valueOf(shortestDistanceInMeters)+ " metres\naway from the nearest Chas Clinic!");
     * @param mMap which GMap to add marker to
     * @param latLng coordinates of the marker that's being added
     * @param title String of title if needed in marker's infoWindow
     * @param snippet String of snippet if needed in marker's infoWindow
     */
    public static void createMarker (GoogleMap mMap, LatLng latLng, String title, String snippet) {
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(title)
                .snippet(snippet));
    }

}
