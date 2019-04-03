package com.example.medicaldiagnosisapp;

<<<<<<< Updated upstream
import android.Manifest;
import android.content.pm.PackageManager;
=======
import android.content.Context;
import android.content.Intent;
>>>>>>> Stashed changes
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

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

<<<<<<< Updated upstream
        /**
         * Check for user button click
         * Activates AedFragment
         */
        aedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new AedFragment(), false, "aed");
=======
        imageViewPolyclinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = LocateActivity.this;
                Class destinationActivity = PolyActivity.class;
                Intent startIntent = new Intent(context, destinationActivity);
                startActivity(startIntent);
>>>>>>> Stashed changes
            }
        });

    }

}