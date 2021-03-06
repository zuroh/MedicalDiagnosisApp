package com.example.medicaldiagnosisapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicaldiagnosisapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Admin Activity Final displays the data logs based on the filters
 * selected in the past few Admin Activities 1,2,3
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class AdminActivityFinal extends AppCompatActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference logRef;

    private static final int LOCATION_REQUEST_CODE = 991;
    private GoogleMap mMap;
    private ArrayList<Marker> mMarkerArray = new ArrayList<Marker>();

    /**
     * creates the lifecycle of an android activity
     * @param savedInstanceState Bundle is passed to enable the past lifecycle of the activity to be resumed
     */
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
                                break regionsearch; //break 2 (for loops) with regionsearch:
                            }
                        }
                    }
                }
                //Dynamic text view
                TextView totalCase = (TextView) findViewById(R.id.textViewTotalCase);
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
                                FirebaseAuth.getInstance().signOut();
                                Intent moveToMainAct = new Intent(getApplicationContext(), MainActivity.class);
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

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(10.0f);
        mMap.setMaxZoomPreference(16.0f);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.35, 103.8), 11));

        LatLngBounds SINGAPORE = new LatLngBounds(
                new LatLng(1.152761, 103.559083), new LatLng(1.487512, 104.113698));
        mMap.setLatLngBoundsForCameraTarget(SINGAPORE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
        }
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

            TextView tvTitle = ((TextView) myContentsView.findViewById(R.id.emergency_service)); //just reuse existing
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
