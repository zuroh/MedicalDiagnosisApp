package com.example.medicaldiagnosisapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.medicaldiagnosisapp.R;

/**
 * Locate Activity with the commonly required amenities that are health related.
 * Its corresponding amenities can be accessed through their respective buttons.
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class LocateActivity extends AppCompatActivity {

    /**
     * creates the lifecycle of an android activity
     * @param savedInstanceState Bundle is passed to enable the past lifecycle of the activity to be resumed
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);

        ImageView imageViewAed = findViewById(R.id.imageViewAed);
        ImageView imageViewChas = findViewById(R.id.imageViewChas);
        ImageView imageViewPolyclinic = findViewById(R.id.imageViewPolyclinic);

        imageViewAed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = LocateActivity.this;
                Class destinationActivity = AedActivity.class;
                Intent startIntent = new Intent(context, destinationActivity);
                startActivity(startIntent);
            }
        });

        imageViewChas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = LocateActivity.this;
                Class destinationActivity = ChasActivity.class;
                Intent startIntent = new Intent(context, destinationActivity);
                startActivity(startIntent);
            }
        });

        imageViewPolyclinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = LocateActivity.this;
                Class destinationActivity = PolyActivity.class;
                Intent startIntent = new Intent(context, destinationActivity);
                startActivity(startIntent);
            }
        });

        //Bottom Navigation Start//

        BottomNavigationView btmNavMenu = (BottomNavigationView) findViewById(R.id.btm_navigation_menu);

        btmNavMenu.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_action_info:
                                Intent moveToInfo = new Intent(getApplicationContext(), InformationActivity.class);
                                startActivity(moveToInfo);
                                break;
                            case R.id.nav_action_call:
                                Intent moveToContact = new Intent(getApplicationContext(), ContactActivity.class);
                                startActivity(moveToContact);
                                break;
                            case R.id.nav_action_diagnose:
                                Intent moveToDiagnose1 = new Intent(getApplicationContext(), DiagnoseActivityPage1.class);
                                startActivity(moveToDiagnose1);
                                break;
                            case R.id.nav_action_locate:
                                Intent moveToLocate = new Intent(getApplicationContext(), LocateActivity.class);
                                startActivity(moveToLocate);
                                break;
                            case R.id.nav_action_help:
                                Intent moveToHelp = new Intent(getApplicationContext(), HelpActivity.class);
                                startActivity(moveToHelp);
                                break;
                        }
                        return true;
                    }

                });

        //Bottom navigation end//


    }

}