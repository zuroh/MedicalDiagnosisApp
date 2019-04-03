package com.example.medicaldiagnosisapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.medicaldiagnosisapp.fragments.AedFragment;

public class LocateActivity extends AppCompatActivity {

    /**
     * On Activity creation, restores its prior information if available
     *
     * @param savedInstanceState contains previous instance if available
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);

        ImageView imageViewAed = findViewById(R.id.imageViewAed);
        ImageView imageViewChas = findViewById(R.id.imageViewChas);
        ImageView imageViewPolyclinic = findViewById(R.id.imageViewPolyclinic);

        /**
         * Check for user button click
         * Goes to the specified activity
         */
        imageViewAed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = LocateActivity.this;
                Class destinationActivity = AedActivity.class;
                Intent startIntent = new Intent(context, destinationActivity);
                startActivity(startIntent);
            }
        });

        imageViewChas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = LocateActivity.this;
                Class destinationActivity = ChasActivity.class;
                Intent startIntent = new Intent(context, destinationActivity);
                startActivity(startIntent);
            }
        });

        imageViewPolyclinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = LocateActivity.this;
                Class destinationActivity = PolyActivity.class;
                Intent startIntent = new Intent(context, destinationActivity);
                startActivity(startIntent);
            }
        });

    }

}