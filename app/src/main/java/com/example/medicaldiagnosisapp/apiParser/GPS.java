package com.example.medicaldiagnosisapp.apiParser;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.medicaldiagnosisapp.interfaces.IGPSInterface;

public class GPS {
    //instantiate interface class for long lat coordinates.
    private IGPSInterface main;

    //helpers for GPS-Position
    private LocationListener mlocListener;
    private LocationManager mlocManager;
    private boolean isRunning;

    /**
     * Start an instance of MyLocationListener to check for the current location
     * @param main an instance and only one instance of the interface class
     * @param fragmentA the fragment activity that called the GPS java
     */
    public GPS(IGPSInterface main, FragmentActivity fragmentA) {
        this.main = main;

        // GPS Position
        mlocManager = (LocationManager) fragmentA.getSystemService(Context.LOCATION_SERVICE);
        mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        // GPS Position END
        this.isRunning = true;
        Log.i("FieldLayout_GPS", "GPS Object created");
    }

    /**
     * Stops the instance of MyLocationListener from constantly checking current
     * location when the activity is paused/stopped
     */
    public void stopGPS() {
        if(isRunning) {
            if(mlocManager==null)
                Log.i("FieldLayout_GPS", "no manager");
            if(mlocListener==null)
                Log.i("FieldLayout_GPS", "no listener");
            mlocManager.removeUpdates(mlocListener);
            this.isRunning = false;
        }
        Log.i("FieldLayout_GPS", "stopGPS");
    }

    /**
     * Starts the same instance of MyLocationListener to check current location
     */
    public void resumeGPS() {
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        this.isRunning = true;
        Log.i("FieldLayout_GPS", "resumeGPS");
    }

    /**
     * @return isRunning Boolean variables that indicates whether an instance of
     * MyLocationListener is already running to prevent multiple MyLocationListener
     * instances
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Implements the LocationListener as required before usage
     */
    public class MyLocationListener implements LocationListener {

        private final String TAG = MyLocationListener.class.getSimpleName();

        @Override
        public void onLocationChanged(Location loc) {
            GPS.this.main.locationChanged(loc.getLongitude(), loc.getLatitude());
            Log.i("FieldLayout_GPS", "onLocationChanged");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i("FieldLayout_GPS", "onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.i("FieldLayout_GPS", "onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.i("FieldLayout_GPS", "onProviderDisabled");
        }
    }
}