package com.example.medicaldiagnosisapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.medicaldiagnosisapp.R;

/**
 * Information Activity that display the 8 common illnesses that we have
 * selected. Its respective activities containing the diagnosis
 * can be accessed from this activity using the buttons.
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class InformationActivity extends AppCompatActivity {

    /**
     * creates the lifecycle of an android activity
     * @param savedInstanceState Bundle is passed to enable the past lifecycle of the activity to be resumed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

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


        //8 Medical Conditions Guide Here//
        Button strokeButton = findViewById(R.id.strokeButton);
        strokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToStroke = new Intent(getApplicationContext(),StrokeActivity.class);
                startActivity(moveToStroke);
            }
        });
        Button heartAttackButton = findViewById(R.id.heartAttackButton);
        heartAttackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToHeart = new Intent(getApplicationContext(),HeartAttackActivity.class);
                startActivity(moveToHeart);
            }
        });
        Button burnButton = findViewById(R.id.burnButton);
        burnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToBurn = new Intent(getApplicationContext(),BurnActivity.class);
                startActivity(moveToBurn);
            }
        });
        Button traumaButton = findViewById(R.id.traumaButton);
        traumaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToTrauma = new Intent(getApplicationContext(),MajorTraumaActivity.class);
                startActivity(moveToTrauma);
            }
        });
        Button boneButton = findViewById(R.id.boneButton);
        boneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToBone = new Intent(getApplicationContext(),BoneActivity.class);
                startActivity(moveToBone);
            }
        });
        Button allergicButton = findViewById(R.id.allergicButton);
        allergicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToAllergy = new Intent(getApplicationContext(),AllergicActivity.class);
                startActivity(moveToAllergy);
            }
        });
        Button poisonButton = findViewById(R.id.poisonButton);
        poisonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToPoison = new Intent(getApplicationContext(),PoisonActivity.class);
                startActivity(moveToPoison);
            }
        });
        Button heatStrokeButton = findViewById(R.id.heatStrokeButton);
        heatStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToHeat = new Intent(getApplicationContext(),HeatActivity.class);
                startActivity(moveToHeat);
            }
        });
    }
}
