package com.example.medicaldiagnosisapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.example.medicaldiagnosisapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2);

        Bundle extras = getIntent().getExtras();
        final HashMap<String, Object> adminView = (HashMap<String, Object>) extras.get("adminView");

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String curDate = df.format(c);
        final String[] startEndDate = {curDate, curDate};
        adminView.put("Start/End Date", startEndDate);

        CalendarView fromCal = findViewById(R.id.calendarView1);
        fromCal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar startDate = Calendar.getInstance();
                startDate.set(year, month, dayOfMonth);
                String dayS; String monthS;
                monthS = Integer.toString(month+1);
                dayS = Integer.toString(dayOfMonth);
                if (month <9){
                    monthS = "0" + monthS;
                }
                if (dayOfMonth <10){
                    dayS = "0" + dayS;
                }
                String date = Integer.toString(year) + "/" + monthS + "/" + dayS;
                startEndDate[0] = date;
            }
        });

        CalendarView toCal = findViewById(R.id.calendarView2);
        toCal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar startDate = Calendar.getInstance();
                startDate.set(year, month, dayOfMonth);
                String dayS; String monthS;
                monthS = Integer.toString(month+1);
                dayS = Integer.toString(dayOfMonth);
                if (month <9){
                    monthS = "0" + monthS;
                }
                if (dayOfMonth <10){
                    dayS = "0" + dayS;
                }
                String date = Integer.toString(year) + "/" + monthS + "/" + dayS;
                startEndDate[1] = date;
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
                Intent moveToAdminAct3 = new Intent(getApplicationContext(),AdminActivity3.class);
                moveToAdminAct3.putExtra("adminView", adminView);
                startActivity(moveToAdminAct3);
            }
        });

        //Set variables based on calendar
    }
}
