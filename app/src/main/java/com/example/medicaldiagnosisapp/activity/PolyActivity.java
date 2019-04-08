package com.example.medicaldiagnosisapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicaldiagnosisapp.R;
import com.example.medicaldiagnosisapp.entities.Poly;
import com.example.medicaldiagnosisapp.utilities.PolyAsyncTask;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

/**
 * Poly Activity allows the user to find the Polyclinic
 * that's the nearest to them
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class PolyActivity extends AppCompatActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback, PolyAsyncTask.PolyTaskCallback {

    private static final int LOCATION_REQUEST_CODE = 991;
    private final String TAG = PolyActivity.class.getSimpleName();
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private ArrayList<Marker> mMarkerArray = new ArrayList<Marker>();

    /**
     * creates the lifecycle of an android activity
     * @param savedInstanceState Bundle is passed to enable the past lifecycle of the activity to be resumed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poly);
//         Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    /**
     * Checks whether permissions to access locations has been enabled for the phone
     * @param requestCode the location request code required in integer
     * @param permissions the resulting string after querying the phone for permissions enabled
     * @param grantResults the result of the function; successful or unsuccessful
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (permissions.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));
                        }
                    }
                });
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());

        new PolyAsyncTask(this, this).execute();

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(12.0f);
        mMap.setMaxZoomPreference(20.0f);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.35, 103.8), 12));

        LatLngBounds SINGAPORE = new LatLngBounds(
                new LatLng(1.152761, 103.559083), new LatLng(1.487512, 104.113698));
        mMap.setLatLngBoundsForCameraTarget(SINGAPORE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {
            mMap.setMyLocationEnabled(true);
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));
                    }
                }
            });
        }

    }

    /**
     * Part of AsynTask implementation
     * Executed before AsynTask starts
     */
    @Override
    public void onPreExecutePolyTask() {
    }

    /**
     * Part of AsynTask implementation
     * Executed after AsynTask starts
     * @param polys Object array that contain all the markers to add them
     */
    @Override
    public void onPostExecutePolyTask(Poly[] polys) {
        for (Poly poly : polys) {
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(poly.getLatLng())
                    .title(poly.getName())
                    .snippet((poly.getAddress())));
            mMarkerArray.add(marker);
        }
    }

    /**
     * Checks for user button click on "Find Nearest",
     * determines the nearest marker to user's location,
     * selects it and zoom in on that marker
     * @param v View that accepts user input
     */
    public void buttonClick(View v) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {
            mMap.setMyLocationEnabled(true);
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null && !mMarkerArray.isEmpty()) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(findNearest(location), 15.0f));
                    }
                }
            });
        }

    }

    /**
     * Finds the nearest marker from the user's current location
     * @param myLoc the Location of user's current location
     * @return nearestLoc the Latlng of the marker that is nearest
     */
    private LatLng findNearest(Location myLoc) {
        LatLng nearestLoc = new LatLng(0, 0);
        LatLng myLatLng = new LatLng(myLoc.getLatitude(), myLoc.getLongitude());
        Marker nearestMark = null;

        for (Marker marker : mMarkerArray) {
            LatLng temp = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
            if (getDistance(temp, myLatLng) <= getDistance(nearestLoc, myLatLng)) {
                nearestLoc = temp;
                nearestMark = marker;
            }
        }

        if (nearestMark != null) {
            nearestMark.showInfoWindow();
        }
        return nearestLoc;
    }

    /**
     * Determines the distance between two markers
     * @param LatLng1 Location 1 in latitude and longitude
     * @param LatLng2 Location 2 in latitude and longitude
     * @return distance the double that contains the distance
     */
    public double getDistance(LatLng LatLng1, LatLng LatLng2) {
        double distance = 0;
        Location locationA = new Location("A");
        locationA.setLatitude(LatLng1.latitude);
        locationA.setLongitude(LatLng1.longitude);
        Location locationB = new Location("B");
        locationB.setLatitude(LatLng2.latitude);
        locationB.setLongitude(LatLng2.longitude);
        distance = locationA.distanceTo(locationB);
        return distance;
    }

    /**
     * Adapter for the Info Window in Google Maps
     * Defined in this class to extract title and snippet for display in google map fragment
     */
    public class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MyInfoWindowAdapter() {
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_windows, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView) myContentsView.findViewById(R.id.emergency_service));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView) myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

    }

}