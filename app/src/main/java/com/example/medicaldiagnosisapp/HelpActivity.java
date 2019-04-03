package com.example.medicaldiagnosisapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

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

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToLogin = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(moveToLogin);
            }
        });
        Button changeLanguagePageButton = findViewById(R.id.changeLanguagePageButton);
        changeLanguagePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToChangeLanguage = new Intent(getApplicationContext(),ChangeLanguageActivity.class);
                startActivity(moveToChangeLanguage);
            }
        });
    }
}
