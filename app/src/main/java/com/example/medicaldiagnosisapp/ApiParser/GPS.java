package com.example.medicaldiagnosisapp.ApiParser;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;

import android.location.LocationListener;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.medicaldiagnosisapp.IGPSActivity;

public class GPS {
    private IGPSActivity main;

    // Helper for GPS-Position
    private LocationListener mlocListener;
    private LocationManager mlocManager;

    private boolean isRunning;

    public GPS(IGPSActivity main, FragmentActivity fragmentA) {
        this.main = main;

        // GPS Position

        LocationManager mlocManager = (LocationManager) fragmentA.getSystemService(Context.LOCATION_SERVICE);
        mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        // GPS Position END
        this.isRunning = true;
        Log.i("FieldLayout_GPS", "GPS Object created");
    }

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

    public void resumeGPS() {
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        this.isRunning = true;
        Log.i("FieldLayout_GPS", "resumeGPS");
    }

    public boolean isRunning() {
        return this.isRunning;
    }

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