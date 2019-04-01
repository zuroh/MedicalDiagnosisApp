package com.example.medicaldiagnosisapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.example.medicaldiagnosisapp.Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;

public class LocateActivity extends AppCompatActivity {

    /**
     * On Activity creation, restores its prior information if available
     * @param savedInstanceState contains previous instance if available
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_aed);

        Button polyButton = findViewById(R.id.poly_button);
        Button chasButton = findViewById(R.id.chas_button);
        Button aedButton = findViewById(R.id.aed_button);

        /**
         * Check for user button click
         * Activates PolyFragment
         */
        polyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new PolyFragment(), false, "poly");
            }
        });

        /**
         * Check for user button click
         * Activates ChasFragment
         */
        chasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new ChasFragment(), false, "chas");
            }
        });

        /**
         * Check for user button click
         * Activates AedFragment
         */
        aedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new AedFragment(), false, "aed");
            }
        });

        /**
         * Check for Location permissions
         * Request if not available
         */
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            return;
        }

    }

    /**
     * Manages the fragments that occupy a container in the layout
     * @param fragment the fragment that will be occupying the container
     * @param addToBackStack if an existing fragment is in the container, add its tag
     *                       to stack for recall later
     * @param tag identity of the fragments. prevent multiple instances of same fragment
     */
    public void addFragment(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.container_frame_back, fragment, tag);
        ft.commitAllowingStateLoss();
    }
}