package com.example.medicaldiagnosisapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin1);

        final HashMap<String, Object> adminView = new HashMap<String, Object>();
        final ArrayList<String> condition = new ArrayList<String>();
        adminView.put("Conditions", condition);

        CheckBox chkheart = findViewById(R.id.chkBox1);
        chkheart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    condition.add("Heart Attack");
                }
            }
        });

        CheckBox chkstroke = findViewById(R.id.chkBox2);
        chkstroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    condition.add("Stroke");
                }
            }
        });

        CheckBox chkpoison = findViewById(R.id.chkBox3);
        chkpoison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    condition.add("Poison");
                }
            }
        });

        CheckBox chkana = findViewById(R.id.chkBox4);
        chkana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    condition.add("Anaphylaxis");
                }
            }
        });

        CheckBox chkheat = findViewById(R.id.chkBox5);
        chkheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    condition.add("Heat Stroke");
                }
            }
        });

        CheckBox chkburn = findViewById(R.id.chkBox6);
        chkburn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    condition.add("Burns");
                }
            }
        });

        CheckBox chktrau = findViewById(R.id.chkBox7);
        chktrau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    condition.add("Major Trauma");
                }
            }
        });

        CheckBox chkbone = findViewById(R.id.chkBox8);
        chkbone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    condition.add("Bone Fracture/Dislocation");
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
                                Intent moveToMainAct = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(moveToMainAct);
                                break;
                            case R.id.action_admin_restart:
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
                Intent moveToAdminAct2 = new Intent(getApplicationContext(),AdminActivity2.class);
                moveToAdminAct2.putExtra("adminView", adminView);
                startActivity(moveToAdminAct2);
            }
        });

        //Set variables based on checkbox
    }
}
