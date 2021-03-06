package com.example.medicaldiagnosisapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.medicaldiagnosisapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Admin Activity allows the user to specify another filter
 * for the data logs - by regions
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class AdminActivity3 extends AppCompatActivity {

    /**
     * creates the lifecycle of an android activity
     * @param savedInstanceState Bundle is passed to enable the past lifecycle of the activity to be resumed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin3);

        Bundle extras = getIntent().getExtras();
        final HashMap<String, Object> adminView = (HashMap<String, Object>) extras.get("adminView");
        final ArrayList<String> regions = new ArrayList<String>();
        adminView.put("Regions", regions);

        CheckBox chknorth = findViewById(R.id.checkBox1);
        chknorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    regions.add("North");
                }
            }
        });

        CheckBox chksouth = findViewById(R.id.checkBox2);
        chksouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    regions.add("South");
                }
            }
        });

        CheckBox chkeast = findViewById(R.id.checkBox3);
        chkeast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    regions.add("East");
                }
            }
        });

        CheckBox chkwest = findViewById(R.id.checkBox4);
        chkwest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    regions.add("West");
                }
            }
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

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToAdminActFinal = new Intent(getApplicationContext(),AdminActivityFinal.class);
                moveToAdminActFinal.putExtra("adminView", adminView);
                startActivity(moveToAdminActFinal);
            }
        });

        //Set variables based on checkbox
    }
}
