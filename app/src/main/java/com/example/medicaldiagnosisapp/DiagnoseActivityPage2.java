package com.example.medicaldiagnosisapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicaldiagnosisapp.ApiParser.GPS;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class DiagnoseActivityPage2 extends AppCompatActivity implements IGPSActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose_page2);

        //start the current location tracking
        gps = new GPS(this, this);

        //get the array from previous activity

        Intent getfrompage1 = getIntent();
        Bundle extras = getIntent().getExtras();
        final double [] diagnoseArr = extras.getDoubleArray("diagnoseArr");
        final String victimGender = getfrompage1.getStringExtra(DiagnoseActivityPage1.extraVictimGender);
        final int victimAge = getfrompage1.getIntExtra(DiagnoseActivityPage1.extraVictimAge,0);

        ScrollView hsv1 = (ScrollView)findViewById(R.id.hsv1);
        LinearLayout ll = (LinearLayout)hsv1.findViewById(R.id.hsvLayout1);

        TextView testGender = (TextView) findViewById(R.id.testgender);
        TextView testAge = (TextView) findViewById(R.id.testage);
        TextView testArray = (TextView) findViewById(R.id.arraytestoutput);
        testGender.setText(victimGender);
        testAge.setText("" + victimAge);

        //testing array values output
        int arraylength = diagnoseArr.length;

        for(int i=0;i<arraylength;i++){
            String temp = Double.toString(diagnoseArr[i]);
            testArray.append(temp);
        }
        //Test to dynamically add checkboxes

        Resources res = getResources();
        String[] NonPhysicalGS = res.getStringArray(R.array.NonPhysicalGS);
        String[] PhysicalGS = res.getStringArray(R.array.PhysicalGS);

        if (diagnoseArr[0]==1) {
            CheckBox[] PhysicalGSCheckBox = new CheckBox[PhysicalGS.length];

            for (int i = 0; i < PhysicalGS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                PhysicalGSCheckBox[i] = new CheckBox(this);
                PhysicalGSCheckBox[i].setLayoutParams(params);
                PhysicalGSCheckBox[i].setText(PhysicalGS[i]);
                ll.addView(PhysicalGSCheckBox[i]);
                //have to do this method because cannot call i from innerclass, each statement is for each different checkbox
                switch(i){
                    case(0): //pain symptom
                        PhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[8]=diagnoseArr[8]+1;
                                    diagnoseArr[9]=diagnoseArr[9]+1;
                                    diagnoseArr[10]=diagnoseArr[10]+1;
                                }

                            }

                        });
                        break;
                    case(1): //bleeding symptom
                        PhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[9]=diagnoseArr[9]+1;
                                    diagnoseArr[10]=diagnoseArr[10]+1;
                                }

                            }

                        });
                        break;
                    case(2): //swelling symptom
                        PhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[8]=diagnoseArr[8]+1;
                                    diagnoseArr[9]=diagnoseArr[9]+1;
                                    diagnoseArr[10]=diagnoseArr[10]+1;
                                }

                            }

                        });
                        break;
                    case(3): //Shock
                        PhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[8]=diagnoseArr[8]+1;
                                    diagnoseArr[9]=diagnoseArr[9]+1;
                                    diagnoseArr[10]=diagnoseArr[10]+1;
                                }

                            }

                        });
                        break;
                    case(4): //Painless Wounds
                        PhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[8]=diagnoseArr[8]+1;
                                    diagnoseArr[10]=diagnoseArr[10]+1;
                                }

                            }

                        });
                        break;
                    case(5)://Abnormal Wound Color
                        PhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[8]=diagnoseArr[8]+1;
                                }

                            }

                        });
                        break;
                    case(6)://Severe Bleeding
                        PhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[9]=diagnoseArr[9]+1;
                                    diagnoseArr[10]=diagnoseArr[10]+1;
                                    diagnoseArr[11]=diagnoseArr[11]+1;
                                }

                            }

                        });
                        break;
                    case(7): //Trouble Breathing
                        PhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[8]=diagnoseArr[8]+1;
                                    diagnoseArr[9]=diagnoseArr[9]+1;
                                    diagnoseArr[11]=diagnoseArr[11]+1;
                                }

                            }

                        });
                        break;
                    case(8): //wounds more than 30 percent
                        PhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[11]=diagnoseArr[11]+1;
                                }

                            }

                        });
                        break;
                    case(9): //Difficulty moving injured area
                        PhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[9]=diagnoseArr[9]+1;
                                    diagnoseArr[10]=diagnoseArr[10]+1;
                                }

                            }

                        });
                        break;
                }



            }
        }
        else if(diagnoseArr[0]==0) {
            CheckBox[] NonPhysicalGSCheckBox = new CheckBox[NonPhysicalGS.length];

            for (int i = 0; i < NonPhysicalGS.length; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 30, 0, 0);


                NonPhysicalGSCheckBox[i] = new CheckBox(this);
                NonPhysicalGSCheckBox[i].setLayoutParams(params);
                NonPhysicalGSCheckBox[i].setText(NonPhysicalGS[i]);
                ll.addView(NonPhysicalGSCheckBox[i]);
                switch(i){
                    case(0): //Dizzyness
                        NonPhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[3]=diagnoseArr[3]+1;
                                    diagnoseArr[6]=diagnoseArr[6]+1;
                                    diagnoseArr[7]=diagnoseArr[7]+1;

                                }

                            }
                        });
                        break;
                    case(1): //nausea
                        NonPhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[3]=diagnoseArr[3]+1;
                                    diagnoseArr[7]=diagnoseArr[7]+1;

                                }

                            }
                        });
                        break;
                    case(2): //confusion
                        NonPhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[4]=diagnoseArr[4]+1;
                                    diagnoseArr[5]=diagnoseArr[5]+1;
                                    diagnoseArr[6]=diagnoseArr[6]+1;

                                }

                            }
                        });
                        break;
                    case(3): //Muscle Spasm
                        NonPhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[4]=diagnoseArr[4]+1;
                                    diagnoseArr[5]=diagnoseArr[5]+1;
                                    diagnoseArr[6]=diagnoseArr[6]+1;

                                }

                            }
                        });
                        break;
                    case(4): //Drooling
                        NonPhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[4]=diagnoseArr[4]+1;
                                    diagnoseArr[5]=diagnoseArr[5]+1;

                                }

                            }
                        });
                        break;
                    case(5): //Unconscious
                        NonPhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[6]=diagnoseArr[6]+1;
                                    diagnoseArr[7]=diagnoseArr[7]+1;
                                    diagnoseArr[11]=diagnoseArr[11]+1;

                                }

                            }
                        });
                        break;
                    case(6): //Shortness of breath/Trouble breathing
                        NonPhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[3]=diagnoseArr[3]+1;
                                    diagnoseArr[5]=diagnoseArr[5]+1;
                                    diagnoseArr[7]=diagnoseArr[7]+1;
                                    diagnoseArr[11]=diagnoseArr[11]+1;

                                }

                            }
                        });
                        break;
                    case(7): //Vomiting
                        NonPhysicalGSCheckBox[i].setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                boolean checked = ((CheckBox)v).isChecked();
                                if(checked){
                                    diagnoseArr[3]=diagnoseArr[3]+1;
                                    diagnoseArr[5]=diagnoseArr[5]+1;

                                }

                            }
                        });
                        break;
                }

            }
        }
        else{
            //return error message here
        }

        //Toolbar Buttons Start Here

        Button diagnoseButton = (Button) findViewById(R.id.diagnoseButton);
        diagnoseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToDiagnose1 = new Intent(getApplicationContext(), DiagnoseActivityPage1.class);
                startActivity(moveToDiagnose1);
            }
        });

        ImageButton helpButton =  findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent moveToHelp = new Intent(getApplicationContext(),HelpActivity.class);
                startActivity(moveToHelp);
            }
        });
        ImageButton locateButton =  findViewById(R.id.locateButton);
        locateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent moveToLocate = new Intent(getApplicationContext(),LocateActivity.class);
                startActivity(moveToLocate);
            }
        });
        ImageButton contactPageButton = findViewById(R.id.contactButton);
        contactPageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent moveToContact = new Intent(getApplicationContext(),ContactActivity.class);
                startActivity(moveToContact);
            }
        });
        ImageButton infoButton =  findViewById(R.id.infoButton);
        infoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent moveToInfo = new Intent(getApplicationContext(),InformationActivity.class);
                startActivity(moveToInfo);
            }
        });
        //Toolbar Buttons End Here//

        Button diagnose3Button = findViewById(R.id.diagnose3Button);
        diagnose3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToDiagnose3 = new Intent(getApplicationContext(),DiagnoseActivityPage3.class);
                moveToDiagnose3.putExtra("diagnoseArr",diagnoseArr);
                moveToDiagnose3.putExtra("victimGender",victimGender);
                moveToDiagnose3.putExtra("victimAge",victimAge);
                startActivity(moveToDiagnose3);
            }
        });
        Button finalDiagnoseButton = findViewById(R.id.finalDiagnoseButton);
        finalDiagnoseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToFinalDiagnosis = new Intent(getApplicationContext(),FinalDiagnosisActivity.class);
                Intent moveToHeart = new Intent(getApplicationContext(),HeartAttackActivity.class);
                Intent moveToStroke = new Intent(getApplicationContext(),StrokeActivity.class);
                Intent moveToPoison = new Intent(getApplicationContext(),PoisonActivity.class);
                Intent moveToHeat = new Intent(getApplicationContext(),HeatActivity.class);
                Intent moveToAllergy = new Intent(getApplicationContext(),AllergicActivity.class);
                Intent moveToBurn = new Intent(getApplicationContext(),BurnActivity.class);
                Intent moveToTrauma = new Intent(getApplicationContext(),MajorTraumaActivity.class);
                Intent moveToBone = new Intent(getApplicationContext(),BoneActivity.class);

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                String date = df.format(c);
                double longitude = currentLocation.getLongitude();
                double latitude = currentLocation.getLatitude();

                double MaleHA = 1;
                double FemaleHA = 1;
                double MaleHARatio = MaleHA/FemaleHA;
                double FemaleHARatio = FemaleHA/MaleHA;

                double MaleStroke = 1;
                double FemaleStroke = 1;
                double MaleStrokeRatio = MaleStroke/FemaleStroke;
                double FemaleStrokeRatio = FemaleStroke/MaleStroke;

                if((victimGender == "Male")||victimGender == "male"){
                    diagnoseArr[3]=diagnoseArr[3]*MaleHARatio;
                    diagnoseArr[4]=diagnoseArr[4]*MaleStrokeRatio;
                }
                else{
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
                        new DataLogTask().execute(DataLog1);

                        Toast toast1 = Toast.makeText(getApplicationContext(),
                                "Diagnosis Created!",
                                Toast.LENGTH_SHORT);

                        toast1.show();

                        startActivity(moveToHeart);
                        break;
                    case(4):
                        DataLog DataLog2 = new DataLog(date,latitude,longitude,"Stroke");
                        new DataLogTask().execute(DataLog2);

                        Toast toast2 = Toast.makeText(getApplicationContext(),
                                "Diagnosis Created!",
                                Toast.LENGTH_SHORT);

                        toast2.show();
                        startActivity(moveToStroke);
                        break;
                    case(5):
                        DataLog DataLog3 = new DataLog(date,latitude,longitude,"Poison");
                        new DataLogTask().execute(DataLog3);

                        Toast toast3 = Toast.makeText(getApplicationContext(),
                                "Diagnosis Created!",
                                Toast.LENGTH_SHORT);

                        toast3.show();
                        startActivity(moveToPoison);
                        break;
                    case(6):
                        DataLog DataLog4 = new DataLog(date,latitude,longitude,"Heat Stroke");
                        new DataLogTask().execute(DataLog4);

                        Toast toast4 = Toast.makeText(getApplicationContext(),
                                "Diagnosis Created!",
                                Toast.LENGTH_SHORT);

                        toast4.show();
                        startActivity(moveToHeat);
                        break;
                    case(7):
                        DataLog DataLog5 = new DataLog(date,latitude,longitude,"Anaphylaxis");
                        new DataLogTask().execute(DataLog5);

                        Toast toast5 = Toast.makeText(getApplicationContext(),
                                "Diagnosis Created!",
                                Toast.LENGTH_SHORT);

                        toast5.show();
                        startActivity(moveToAllergy);
                        break;
                    case(8):
                        DataLog DataLog6 = new DataLog(date,latitude,longitude,"Burns");
                        new DataLogTask().execute(DataLog6);

                        Toast toast6 = Toast.makeText(getApplicationContext(),
                                "Diagnosis Created!",
                                Toast.LENGTH_SHORT);

                        toast6.show();
                        startActivity(moveToBurn);
                        break;
                    case(9):
                        DataLog DataLog7 = new DataLog(date,latitude,longitude,"Major Trauma");
                        new DataLogTask().execute(DataLog7);

                        Toast toast7 = Toast.makeText(getApplicationContext(),
                                "Diagnosis Created!",
                                Toast.LENGTH_SHORT);

                        toast7.show();
                        startActivity(moveToTrauma);
                        break;
                    case(10):
                        DataLog DataLog8 = new DataLog(date,latitude,longitude,"Bone Fracture/Dislocation");
                        new DataLogTask().execute(DataLog8);

                        Toast toast8 = Toast.makeText(getApplicationContext(),
                                "Diagnosis Created!",
                                Toast.LENGTH_SHORT);

                        toast8.show();
                        startActivity(moveToBone);
                        break;

                }
            }
        });
    }

}