package com.example.medicaldiagnosisapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;


public class DiagnoseActivityPage1 extends AppCompatActivity {
    public static final String extraVictimGender = "com.example.medicaldiagnosisapp.extraVictimGender";
    public static final String extraVictimAge = "com.example.medicaldiagnosisapp.extraVictimAge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose_page1);

        final double [] diagnoseArr = {0,0,0,0,0,0,0,0,0,0,0,0};
        //array is [phy,ingest,allergy,heart,stroke,poison,heat,anaphy,burn,trauma,bone]


        CheckBox chkphysical = findViewById(R.id.checkPhysical);
        chkphysical.setText("Any Visible Physical Symptoms?");
        chkphysical.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    diagnoseArr[0]=diagnoseArr[0]+1;

                }
                else{
                    diagnoseArr[0]=0;

                }
            }
        });
        CheckBox chkingest = findViewById(R.id.checkIngest);
        chkingest.setText("Has Victim Ingested Anything recently?");
        chkingest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    diagnoseArr[1]=diagnoseArr[1]+1;
                    diagnoseArr[5]=diagnoseArr[5]+3;
                }
                else{
                    diagnoseArr[1]=0;
                }
            }
        });
        CheckBox chkallergen = findViewById(R.id.checkAllergen);
        chkallergen.setText("Has the victim been exposed to suspected allergens?");
        chkallergen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean checked = ((CheckBox)v).isChecked();
                if (checked){
                    diagnoseArr[2]=diagnoseArr[2]+1;
                    diagnoseArr[7]=diagnoseArr[7]+3;
                }
                else{
                    diagnoseArr[2]=0;
                }
            }
        });



        Button diagnose2Button = findViewById(R.id.diagnose2Button);
        diagnose2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get Age and store as a variable to be passed later
                EditText getAge = findViewById(R.id.enterAge);
                int victimAge = Integer.parseInt(getAge.getText().toString());


                //get Gender and store as a variable to be passed later
                EditText getGender = findViewById(R.id.enterGender);
                String stringGender = getGender.getText().toString();

                Intent moveToDiagnose2 = new Intent(getApplicationContext(),DiagnoseActivityPage2.class);
                moveToDiagnose2.putExtra(extraVictimAge,victimAge);
                moveToDiagnose2.putExtra(extraVictimGender,stringGender);
                moveToDiagnose2.putExtra("diagnoseArr",diagnoseArr);
                startActivity(moveToDiagnose2);

            }
        });
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


    }
}