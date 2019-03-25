package com.example.medicaldiagnosisapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AdminActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2);

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

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToAdminAct3 = new Intent(getApplicationContext(),AdminActivity3.class);
                startActivity(moveToAdminAct3);
            }
        });

        //Set variables based on calendar
    }
}
