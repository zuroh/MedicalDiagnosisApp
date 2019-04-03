package com.example.medicaldiagnosisapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;

public class AdminActivityFinal extends AppCompatActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference logRef;

    private static final int LOCATION_REQUEST_CODE = 991;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private ArrayList<Marker> mMarkerArray = new ArrayList<Marker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_final);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        logRef = mDatabaseRef.child("logs");

        Bundle extras = getIntent().getExtras();
        final HashMap<String, Object> adminView = (HashMap<String, Object>) extras.get("adminView");
        String[] startEndDate = (String[]) adminView.get("Start/End Date");
        final ArrayList<String> condition = (ArrayList<String>) adminView.get("Conditions");
        final ArrayList<String> regions = (ArrayList<String>) adminView.get("Regions");
        final String startDate = startEndDate[0];
        final String endDate = startEndDate[1];

        Query query = logRef.orderByChild("date").startAt(startEndDate[0]).endAt(startEndDate[1]);

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                String region = (String) dataSnapshot.child("region").getValue();
                String type = (String) dataSnapshot.child("type").getValue();

                regionsearch:
                for(String regionChosen : regions){
                    if(region.equals(regionChosen)){
                        for(String conditionChosen : condition){
                            if(type.equals(conditionChosen)){
                                //get coordinates
                                double latitude = (double) dataSnapshot.child("latitude").getValue();
                                double longitude = (double) dataSnapshot.child("longitude").getValue();
                                //add marker into array
                                Marker marker = mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(latitude, longitude))
                                        .title(String.valueOf(dataSnapshot.child("date").getValue()))
                                        .snippet(conditionChosen + "\n" + regionChosen + "region"));
                                mMarkerArray.add(marker);
                                Toast t = Toast.makeText(getApplicationContext(), String.valueOf(latitude), Toast.LENGTH_LONG);
                                t.show();
                                break regionsearch; //break 2 (for loops) with regionsearch:
                            }
                        }
                    }
                }
                //Dynamic text view
                TextView totalCase = (TextView) findViewById(R.id.textViewTotalCase);
                Log.d("size", "Confound" + String.valueOf(mMarkerArray.size()));
                totalCase.setText("Total Number of Diagnosis: " + String.valueOf(mMarkerArray.size()));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        //Bottom Toolbar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_admin_exit:
                                Intent moveToMainAct = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(moveToMainAct);
                                break;
                            case R.id.action_admin_restart:
                                Intent moveToAdminAct1 = new Intent(getApplicationContext(),AdminActivity1.class);
                                startActivity(moveToAdminAct1);
                                break;
                        }
                        return true;
                    }
                });
        //Bottom Toolbar end

        //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    //Gmap View
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
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(10.0f);
        mMap.setMaxZoomPreference(16.0f);

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
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 10));
                    }
                }
            });
        }

    }

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

    private LatLng findNearest(Location myLoc) {
        LatLng nearestLoc = new LatLng(0, 0);
        LatLng myLatLng = new LatLng(myLoc.getLatitude(), myLoc.getLongitude());
//        Log.d(TAG, "my latlng = " + myLatLng.latitude + ", " + myLatLng.longitude);
        Marker nearestMark = null;

        for (Marker marker : mMarkerArray) {
            LatLng temp = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
//            Log.d(TAG, "latlng = " + temp.latitude + ", " + temp.longitude);
            if (getDistance(temp, myLatLng) <= getDistance(nearestLoc, myLatLng)) {
                nearestLoc = temp;
//                Log.d(TAG, "current nearest latlng = " + nearestLoc.latitude + ", " + nearestLoc.longitude);
                nearestMark = marker;
            }
        }
//        Log.d(TAG, "my latlng = " + myLatLng.latitude + ", " + myLatLng.longitude);

//        Log.d(TAG, "actual nearest latlng = " + nearestLoc.latitude + ", " + nearestLoc.longitude);
        if (nearestMark != null) {
            nearestMark.showInfoWindow();
        }
        return nearestLoc;
    }

    public double getDistance(LatLng LatLng1, LatLng LatLng2) {
        double distance = 0;
        Location locationA = new Location("A");
        locationA.setLatitude(LatLng1.latitude);
        locationA.setLongitude(LatLng1.longitude);
        Location locationB = new Location("B");
        locationB.setLatitude(LatLng2.latitude);
        locationB.setLongitude(LatLng2.longitude);
        distance = locationA.distanceTo(locationB);
//        Log.d(TAG, "distance = " + distance);
        return distance;
    }

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
    //Gmap View end
}
