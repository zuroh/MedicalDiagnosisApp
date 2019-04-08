package com.example.medicaldiagnosisapp.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.medicaldiagnosisapp.apiParser.GPS;
import com.example.medicaldiagnosisapp.entities.DataLog;
import com.example.medicaldiagnosisapp.utilities.DataLogAsyncTask;
import com.example.medicaldiagnosisapp.interfaces.IGPSInterface;
import com.example.medicaldiagnosisapp.R;
import com.example.medicaldiagnosisapp.apiParser.WebAPIHandler;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Diagnosis Activity 3 that asks for further symptoms the person in peril may have
 * The symptoms in this page is generated dynamically based on Diagnosis Activity 2
 * @author Sheng Rong, Darren, Leonard, Bryan, Kendra
 */
public class DiagnoseActivityPage3 extends AppCompatActivity implements IGPSInterface {

    private Location currentLocation = new Location (LocationManager.GPS_PROVIDER);
    private GPS gps;

    /**
     * When the activity resumes, resume tracking of current location
     */
    @Override
    public void onResume() {
        if (!gps.isRunning()) gps.resumeGPS();
        super.onResume();
    }

    /**
     * When the activity stops, stop tracking of current location
     */
    @Override
    public void onStop() {
        // Disconnecting the client invalidates it.
        Log.i("FieldLayout_StartAct", "onStop called. Disconnecting GPS client");
        gps.stopGPS();
        super.onStop();
    }

    /**
     * Updates the current location if it's changed
     * @param longitude
     * @param latitude used to contains the long lat of the current location
     */
    @Override
    public void locationChanged(double longitude, double latitude) {
        Log.i("FieldLayout_StartAct", "locationChanged");
        currentLocation.setLatitude(latitude);
        currentLocation.setLongitude(longitude);
    }

    /**
     * creates the lifecycle of an android activity
     * @param savedInstanceState Bundle is passed to enable the past lifecycle of the activity to be resumed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose_page3);

        //start the current location tracking
        gps = new GPS(this, this);

        //get the array from previous activity

        Intent getfrompage2 = getIntent();
        Bundle extras = getIntent().getExtras();
        final double [] diagnoseArr = extras.getDoubleArray("diagnoseArr");
        final String victimGender = extras.getString("victimGender");
        final int victimAge = extras.getInt("victimAge");

        int firstindex=3;
        int secondindex=3;
        int thirdindex=3;

        int z ;
        double first,second,third;
        first = second = third = Double.MIN_VALUE;
        for(z=3;z<10;z++){
            if (diagnoseArr[z] > first)
            {
                third = second;
                second = first;
                first = diagnoseArr[z];
            }

            /* If arr[i] is in between first and
            second then update second  */
            else if (diagnoseArr[z] > second)
            {
                third = second;
                second = diagnoseArr[z];
            }

            else if (diagnoseArr[z] > third)
                third = diagnoseArr[z];
        }
        int j=0;
        int k = 0;
        int l =0;
        while (j < 10) {

            // if the i-th element is t
            // then return the index
            if (diagnoseArr[j] == first) {
                firstindex = j;
                break;
            }
            else {
                j = j + 1;
            }
        }

        while (k < 10) {

            // if the i-th element is t
            // then return the index
            if (diagnoseArr[k] == second) {
                secondindex = k;
                break;
            }
            else {
                k = k + 1;
            }
        }

        while (l < 10) {

            // if the i-th element is t
            // then return the index
            if (diagnoseArr[l] == third) {
                thirdindex = l;
                break;
            }
            else {
                l = l + 1;
            }
        }




        Resources res = getResources();
        String[] HeartAttackSS = res.getStringArray(R.array.HeartAttackSS);
        String[] StrokeSS = res.getStringArray(R.array.StrokeSS);
        String[] PoisonSS = res.getStringArray(R.array.PoisonSS);
        String[] HeatStrokeSS = res.getStringArray(R.array.HeatStrokeSS);
        String[] AnaphylaxisSS = res.getStringArray(R.array.AnaphylaxisSS);
        String[] BurnSS = res.getStringArray(R.array.BurnSS);
        String[] MajorTraumaSS = res.getStringArray(R.array.MajorTraumaSS);
        String[] BoneSS = res.getStringArray(R.array.BoneSS);

        ScrollView hsv1 = (ScrollView)findViewById(R.id.hsv1);
        LinearLayout ll = (LinearLayout)hsv1.findViewById(R.id.hsvLayout1);

        if(firstindex==3){
            CheckBox[] HeartAttackCheckBox = new CheckBox[HeartAttackSS.length];

            for (int i = 0; i < HeartAttackSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                HeartAttackCheckBox[i] = new CheckBox(this);
                HeartAttackCheckBox[i].setLayoutParams(params);
                HeartAttackCheckBox[i].setText(HeartAttackSS[i]);
                ll.addView(HeartAttackCheckBox[i]);
                HeartAttackCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[3]=diagnoseArr[3]+1;
                        }
                    }
                });
            }

        }
        else if(firstindex==4){
            CheckBox[] StrokeCheckBox = new CheckBox[StrokeSS.length];

            for (int i = 0; i < StrokeSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                StrokeCheckBox[i] = new CheckBox(this);
                StrokeCheckBox[i].setLayoutParams(params);
                StrokeCheckBox[i].setText(StrokeSS[i]);
                ll.addView(StrokeCheckBox[i]);
                StrokeCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[4]=diagnoseArr[4]+1;
                        }
                    }
                });
            }


        }
        else if(firstindex==5){
            CheckBox[] PoisonCheckBox = new CheckBox[PoisonSS.length];

            for (int i = 0; i < PoisonSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                PoisonCheckBox[i] = new CheckBox(this);
                PoisonCheckBox[i].setLayoutParams(params);
                PoisonCheckBox[i].setText(PoisonSS[i]);
                ll.addView(PoisonCheckBox[i]);
                PoisonCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[5]=diagnoseArr[5]+1;
                        }
                    }
                });
            }

        }
        else if(firstindex==6){
            CheckBox[] HeatCheckBox = new CheckBox[HeatStrokeSS.length];

            for (int i = 0; i < HeatStrokeSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                HeatCheckBox[i] = new CheckBox(this);
                HeatCheckBox[i].setLayoutParams(params);
                HeatCheckBox[i].setText(HeatStrokeSS[i]);
                ll.addView(HeatCheckBox[i]);
                HeatCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[6]=diagnoseArr[6]+1;
                        }
                    }
                });
            }

        }
        else if(firstindex==7){
            CheckBox[] AllergicCheckBox = new CheckBox[AnaphylaxisSS.length];

            for (int i = 0; i < AnaphylaxisSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                AllergicCheckBox[i] = new CheckBox(this);
                AllergicCheckBox[i].setLayoutParams(params);
                AllergicCheckBox[i].setText(AnaphylaxisSS[i]);
                ll.addView(AllergicCheckBox[i]);
                AllergicCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[7]=diagnoseArr[7]+1;
                        }
                    }
                });
            }

        }
        else if(firstindex==8){
            CheckBox[] BurnCheckBox = new CheckBox[BurnSS.length];

            for (int i = 0; i < BurnSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                BurnCheckBox[i] = new CheckBox(this);
                BurnCheckBox[i].setLayoutParams(params);
                BurnCheckBox[i].setText(BurnSS[i]);
                ll.addView(BurnCheckBox[i]);
                BurnCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[8]=diagnoseArr[8]+1;
                        }
                    }
                });
            }

        }
        else if(firstindex==9){
            CheckBox[] TraumaCheckBox = new CheckBox[MajorTraumaSS.length];

            for (int i = 0; i < MajorTraumaSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                TraumaCheckBox[i] = new CheckBox(this);
                TraumaCheckBox[i].setLayoutParams(params);
                TraumaCheckBox[i].setText(MajorTraumaSS[i]);
                ll.addView(TraumaCheckBox[i]);
                TraumaCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[9]=diagnoseArr[9]+1;
                        }
                    }
                });
            }

        }
        else if(firstindex==10){
            CheckBox[] BoneCheckBox = new CheckBox[BoneSS.length];

            for (int i = 0; i < BoneSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                BoneCheckBox[i] = new CheckBox(this);
                BoneCheckBox[i].setLayoutParams(params);
                BoneCheckBox[i].setText(BoneSS[i]);
                ll.addView(BoneCheckBox[i]);
                BoneCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[10]=diagnoseArr[10]+1;
                        }
                    }
                });
            }

        }
        else{
            //handle error here
        }

        if(secondindex==3){
            CheckBox[] HeartAttackCheckBox = new CheckBox[HeartAttackSS.length];

            for (int i = 0; i < HeartAttackSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                HeartAttackCheckBox[i] = new CheckBox(this);
                HeartAttackCheckBox[i].setLayoutParams(params);
                HeartAttackCheckBox[i].setText(HeartAttackSS[i]);
                ll.addView(HeartAttackCheckBox[i]);
                HeartAttackCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[3]=diagnoseArr[3]+1;
                        }
                    }
                });
            }

        }
        else if(secondindex==4){
            CheckBox[] StrokeCheckBox = new CheckBox[StrokeSS.length];

            for (int i = 0; i < StrokeSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                StrokeCheckBox[i] = new CheckBox(this);
                StrokeCheckBox[i].setLayoutParams(params);
                StrokeCheckBox[i].setText(StrokeSS[i]);
                ll.addView(StrokeCheckBox[i]);
                StrokeCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[4]=diagnoseArr[4]+1;
                        }
                    }
                });
            }


        }
        else if(secondindex==5){
            CheckBox[] PoisonCheckBox = new CheckBox[PoisonSS.length];

            for (int i = 0; i < PoisonSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                PoisonCheckBox[i] = new CheckBox(this);
                PoisonCheckBox[i].setLayoutParams(params);
                PoisonCheckBox[i].setText(PoisonSS[i]);
                ll.addView(PoisonCheckBox[i]);
                PoisonCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[5]=diagnoseArr[5]+1;
                        }
                    }
                });
            }

        }
        else if(secondindex==6){
            CheckBox[] HeatCheckBox = new CheckBox[HeatStrokeSS.length];

            for (int i = 0; i < HeatStrokeSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                HeatCheckBox[i] = new CheckBox(this);
                HeatCheckBox[i].setLayoutParams(params);
                HeatCheckBox[i].setText(HeatStrokeSS[i]);
                ll.addView(HeatCheckBox[i]);
                HeatCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[6]=diagnoseArr[6]+1;
                        }
                    }
                });
            }

        }
        else if(secondindex==7){
            CheckBox[] AllergicCheckBox = new CheckBox[AnaphylaxisSS.length];

            for (int i = 0; i < AnaphylaxisSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                AllergicCheckBox[i] = new CheckBox(this);
                AllergicCheckBox[i].setLayoutParams(params);
                AllergicCheckBox[i].setText(AnaphylaxisSS[i]);
                ll.addView(AllergicCheckBox[i]);
                AllergicCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[7]=diagnoseArr[7]+1;
                        }
                    }
                });
            }

        }
        else if(secondindex==8){
            CheckBox[] BurnCheckBox = new CheckBox[BurnSS.length];

            for (int i = 0; i < BurnSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                BurnCheckBox[i] = new CheckBox(this);
                BurnCheckBox[i].setLayoutParams(params);
                BurnCheckBox[i].setText(BurnSS[i]);
                ll.addView(BurnCheckBox[i]);
                BurnCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[8]=diagnoseArr[8]+1;
                        }
                    }
                });
            }

        }
        else if(secondindex==9){
            CheckBox[] TraumaCheckBox = new CheckBox[MajorTraumaSS.length];

            for (int i = 0; i < MajorTraumaSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                TraumaCheckBox[i] = new CheckBox(this);
                TraumaCheckBox[i].setLayoutParams(params);
                TraumaCheckBox[i].setText(MajorTraumaSS[i]);
                ll.addView(TraumaCheckBox[i]);
                TraumaCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[9]=diagnoseArr[9]+1;
                        }
                    }
                });
            }

        }
        else if(secondindex==10){
            CheckBox[] BoneCheckBox = new CheckBox[BoneSS.length];

            for (int i = 0; i < BoneSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                BoneCheckBox[i] = new CheckBox(this);
                BoneCheckBox[i].setLayoutParams(params);
                BoneCheckBox[i].setText(BoneSS[i]);
                ll.addView(BoneCheckBox[i]);
                BoneCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[10]=diagnoseArr[10]+1;
                        }
                    }
                });
            }

        }
        else{
            //handle error here
        }

        if(thirdindex==3){
            CheckBox[] HeartAttackCheckBox = new CheckBox[HeartAttackSS.length];

            for (int i = 0; i < HeartAttackSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                HeartAttackCheckBox[i] = new CheckBox(this);
                HeartAttackCheckBox[i].setLayoutParams(params);
                HeartAttackCheckBox[i].setText(HeartAttackSS[i]);
                ll.addView(HeartAttackCheckBox[i]);
                HeartAttackCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[3]=diagnoseArr[3]+1;
                        }
                    }
                });
            }

        }
        else if(thirdindex==4){
            CheckBox[] StrokeCheckBox = new CheckBox[StrokeSS.length];

            for (int i = 0; i < StrokeSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                StrokeCheckBox[i] = new CheckBox(this);
                StrokeCheckBox[i].setLayoutParams(params);
                StrokeCheckBox[i].setText(StrokeSS[i]);
                ll.addView(StrokeCheckBox[i]);
                StrokeCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[4]=diagnoseArr[4]+1;
                        }
                    }
                });
            }


        }
        else if(thirdindex==5){
            CheckBox[] PoisonCheckBox = new CheckBox[PoisonSS.length];

            for (int i = 0; i < PoisonSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                PoisonCheckBox[i] = new CheckBox(this);
                PoisonCheckBox[i].setLayoutParams(params);
                PoisonCheckBox[i].setText(PoisonSS[i]);
                ll.addView(PoisonCheckBox[i]);
                PoisonCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[5]=diagnoseArr[5]+1;
                        }
                    }
                });
            }

        }
        else if(thirdindex==6){
            CheckBox[] HeatCheckBox = new CheckBox[HeatStrokeSS.length];

            for (int i = 0; i < HeatStrokeSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                HeatCheckBox[i] = new CheckBox(this);
                HeatCheckBox[i].setLayoutParams(params);
                HeatCheckBox[i].setText(HeatStrokeSS[i]);
                ll.addView(HeatCheckBox[i]);
                HeatCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[6]=diagnoseArr[6]+1;
                        }
                    }
                });
            }

        }
        else if(thirdindex==7){
            CheckBox[] AllergicCheckBox = new CheckBox[AnaphylaxisSS.length];

            for (int i = 0; i < AnaphylaxisSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                AllergicCheckBox[i] = new CheckBox(this);
                AllergicCheckBox[i].setLayoutParams(params);
                AllergicCheckBox[i].setText(AnaphylaxisSS[i]);
                ll.addView(AllergicCheckBox[i]);
                AllergicCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[7]=diagnoseArr[7]+1;
                        }
                    }
                });
            }

        }
        else if(thirdindex==8){
            CheckBox[] BurnCheckBox = new CheckBox[BurnSS.length];

            for (int i = 0; i < BurnSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                BurnCheckBox[i] = new CheckBox(this);
                BurnCheckBox[i].setLayoutParams(params);
                BurnCheckBox[i].setText(BurnSS[i]);
                ll.addView(BurnCheckBox[i]);
                BurnCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[8]=diagnoseArr[8]+1;
                        }
                    }
                });
            }

        }
        else if(thirdindex==9){
            CheckBox[] TraumaCheckBox = new CheckBox[MajorTraumaSS.length];

            for (int i = 0; i < MajorTraumaSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                TraumaCheckBox[i] = new CheckBox(this);
                TraumaCheckBox[i].setLayoutParams(params);
                TraumaCheckBox[i].setText(MajorTraumaSS[i]);
                ll.addView(TraumaCheckBox[i]);
                TraumaCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[9]=diagnoseArr[9]+1;
                        }
                    }
                });
            }

        }
        else if(thirdindex==10){
            CheckBox[] BoneCheckBox = new CheckBox[BoneSS.length];

            for (int i = 0; i < BoneSS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                BoneCheckBox[i] = new CheckBox(this);
                BoneCheckBox[i].setLayoutParams(params);
                BoneCheckBox[i].setText(BoneSS[i]);
                ll.addView(BoneCheckBox[i]);
                BoneCheckBox[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = ((CheckBox)v).isChecked();
                        if(checked){
                            diagnoseArr[10]=diagnoseArr[10]+1;
                        }
                    }
                });
            }

        }
        else{
            //handle error here
        }





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


        //final diagnosis
        Button finalDiagnoseButton = findViewById(R.id.finalDiagnoseButton);
        finalDiagnoseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToHeart = new Intent(getApplicationContext(),HeartAttackActivity.class);
                Intent moveToStroke = new Intent(getApplicationContext(),StrokeActivity.class);
                Intent moveToPoison = new Intent(getApplicationContext(),PoisonActivity.class);
                Intent moveToHeat = new Intent(getApplicationContext(),HeatActivity.class);
                Intent moveToAllergy = new Intent(getApplicationContext(),AllergicActivity.class);
                Intent moveToBurn = new Intent(getApplicationContext(),BurnActivity.class);
                Intent moveToTrauma = new Intent(getApplicationContext(),MajorTraumaActivity.class);
                Intent moveToBone = new Intent(getApplicationContext(),BoneActivity.class);


                //get current date
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                String date = df.format(c);
                double longitude = currentLocation.getLongitude();
                double latitude = currentLocation.getLatitude();

                //URL of the APIs to fetch data
                String dataGovURL = "https://data.gov.sg/api/action/datastore_search?";
                String heartURL = dataGovURL + "resource_id=3d51e1e1-4069-4e04-85d5-ae3a310e98d4&q=%7B%22year%22%3A%222016%22%7D";
                String strokeURL = dataGovURL + "resource_id=f1dfc058-8da9-4b53-b2dc-47260412ee34&q=%7B%22year%22%3A%222015%22%7D";

                String maleHAString="";
                String femaleHAString="";
                String maleStrokeString="";
                String femaleStrokeString="";

                try{
                    //fetch the required heart attack data from the API
                    JSONObject heartJSON = new WebAPIHandler().execute(heartURL).get();
                    JSONObject maleHeart = heartJSON.getJSONObject("result").getJSONArray("records").getJSONObject(0);
                    maleHAString = maleHeart.getString("asir");
                    JSONObject femaleHeart = heartJSON.getJSONObject("result").getJSONArray("records").getJSONObject(1);
                    femaleHAString = femaleHeart.getString("asir");

                    //fetch the required stroke data from the API
                    JSONObject strokeJSON = new WebAPIHandler().execute(strokeURL).get();
                    JSONObject maleStrokeData = strokeJSON.getJSONObject("result").getJSONArray("records").getJSONObject(0);
                    maleStrokeString = maleStrokeData.getString("asir");
                    JSONObject femaleStrokeData = strokeJSON.getJSONObject("result").getJSONArray("records").getJSONObject(1);
                    femaleStrokeString = femaleStrokeData.getString("asir");

                } catch (Exception e) {
                    Log.e("Data.gov.sg", "Exception: " + e.getMessage());
                }

                double MaleHA = Double.parseDouble(maleHAString);
                double FemaleHA = Double.parseDouble(femaleHAString);
                double MaleHARatio = MaleHA/FemaleHA;
                double FemaleHARatio = FemaleHA/MaleHA;

                double MaleStroke = Double.parseDouble(maleStrokeString);
                double FemaleStroke = Double.parseDouble(femaleStrokeString);
                double MaleStrokeRatio = MaleStroke/FemaleStroke;
                double FemaleStrokeRatio = FemaleStroke/MaleStroke;

                if((victimGender.toLowerCase().equals("male"))){
                    diagnoseArr[3]=diagnoseArr[3]*MaleHARatio;
                    diagnoseArr[4]=diagnoseArr[4]*MaleStrokeRatio;
                }
                else if((victimGender.toLowerCase().equals("female"))){
                    diagnoseArr[3]=diagnoseArr[3]*FemaleHARatio;
                    diagnoseArr[4]=diagnoseArr[4]*FemaleStrokeRatio;
                }

                double max = diagnoseArr[3];
                int index = 3;

                for(int j=3;j<10;j++){
                    if(max<diagnoseArr[j]) {
                        max = diagnoseArr[j];
                        index=j;
                    }
                }
                switch(index){
                    case(3):
                        DataLog DataLog1 = new DataLog(date,latitude,longitude,"Heart Attack");
                        new DataLogAsyncTask().execute(DataLog1);



                        startActivity(moveToHeart);
                        break;
                    case(4):
                        DataLog DataLog2 = new DataLog(date,latitude,longitude,"Stroke");
                        new DataLogAsyncTask().execute(DataLog2);


                        startActivity(moveToStroke);
                        break;
                    case(5):
                        DataLog DataLog3 = new DataLog(date,latitude,longitude,"Poison");
                        new DataLogAsyncTask().execute(DataLog3);


                        startActivity(moveToPoison);
                        break;
                    case(6):
                        DataLog DataLog4 = new DataLog(date,latitude,longitude,"Heat Stroke");
                        new DataLogAsyncTask().execute(DataLog4);


                        startActivity(moveToHeat);
                        break;
                    case(7):
                        DataLog DataLog5 = new DataLog(date,latitude,longitude,"Anaphylaxis");
                        new DataLogAsyncTask().execute(DataLog5);


                        startActivity(moveToAllergy);
                        break;
                    case(8):
                        DataLog DataLog6 = new DataLog(date,latitude,longitude,"Burns");
                        new DataLogAsyncTask().execute(DataLog6);


                        startActivity(moveToBurn);
                        break;
                    case(9):
                        DataLog DataLog7 = new DataLog(date,latitude,longitude,"Major Trauma");
                        new DataLogAsyncTask().execute(DataLog7);


                        startActivity(moveToTrauma);
                        break;
                    case(10):
                        DataLog DataLog8 = new DataLog(date,latitude,longitude,"Bone Fracture/Dislocation");
                        new DataLogAsyncTask().execute(DataLog8);


                        startActivity(moveToBone);
                        break;

                }

            }
        });
    }
}
