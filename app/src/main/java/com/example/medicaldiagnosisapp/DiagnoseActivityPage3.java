package com.example.medicaldiagnosisapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class DiagnoseActivityPage3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose_page3);

        //get the array from previous activity

        Intent getfrompage2 = getIntent();
        Bundle extras = getIntent().getExtras();
        final int [] diagnoseArr = extras.getIntArray("diagnoseArr");

        //testing array values output
        TextView testArray2 = (TextView) findViewById(R.id.testarray2);
        int arraylength = diagnoseArr.length;

        for(int i=0;i<arraylength;i++){
            String temp = Integer.toString(diagnoseArr[i]);
            testArray2.append(temp);
        }

        //Check for top 3 conditions based on array.
        int first=diagnoseArr[3];
        int second=diagnoseArr[3];
        int third=diagnoseArr[3];
        int firstindex=0;
        int secondindex=0;
        int thirdindex=0;
        for(int j=3;j<10;j++){
            if(first<diagnoseArr[j]) {
                first = diagnoseArr[j];
                firstindex=j;
            }
        }
        int tempfirst = diagnoseArr[firstindex];
        diagnoseArr[firstindex]=0;
        for(int j=3;j<10;j++){
            if(second<diagnoseArr[j]) {
                second = diagnoseArr[j];
                secondindex=j;
            }
        }
        int tempsecond = diagnoseArr[secondindex];
        diagnoseArr[secondindex] = 0;
        for(int j=3;j<10;j++){
            if(third<diagnoseArr[j]) {
                third = diagnoseArr[j];
                thirdindex=j;
            }
        }
        diagnoseArr[firstindex]=tempfirst;
        diagnoseArr[secondindex]=tempsecond;

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

        //final diagnosis
        Button finalDiagnoseButton = findViewById(R.id.finalDiagnoseButton);
        finalDiagnoseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToFinalDiagnosis = new Intent(getApplicationContext(),FinalDiagnosisActivity.class);
                //startActivity(moveToFinalDiagnosis);
                Intent moveToHeart = new Intent(getApplicationContext(),HeartAttackActivity.class);
                Intent moveToStroke = new Intent(getApplicationContext(),StrokeActivity.class);
                Intent moveToPoison = new Intent(getApplicationContext(),PoisonActivity.class);
                Intent moveToHeat = new Intent(getApplicationContext(),HeatActivity.class);
                Intent moveToAllergy = new Intent(getApplicationContext(),AllergicActivity.class);
                Intent moveToBurn = new Intent(getApplicationContext(),BurnActivity.class);
                Intent moveToTrauma = new Intent(getApplicationContext(),MajorTraumaActivity.class);
                Intent moveToBone = new Intent(getApplicationContext(),BoneActivity.class);

                int max = diagnoseArr[3];
                int index = 3;

                for(int j=3;j<10;j++){
                    if(max<diagnoseArr[j]) {
                        max = diagnoseArr[j];
                        index=j;
                    }
                }
                switch(index){
                    case(3):
                        startActivity(moveToHeart);
                        break;
                    case(4):
                        startActivity(moveToStroke);
                        break;
                    case(5):
                        startActivity(moveToPoison);
                        break;
                    case(6):
                        startActivity(moveToHeat);
                        break;
                    case(7):
                        startActivity(moveToAllergy);
                        break;
                    case(8):
                        startActivity(moveToBurn);
                        break;
                    case(9):
                        startActivity(moveToTrauma);
                        break;
                    case(10):
                        startActivity(moveToBone);
                        break;

                }

            }
        });
    }
}