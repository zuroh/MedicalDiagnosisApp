package com.example.medicaldiagnosisapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField, passwordField;
    private Button loginButton;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        startUI();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailLogin();
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

        Button lginButton = (Button) findViewById(R.id.lginButton);
        lginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToAdmin1 = new Intent(getApplicationContext(), AdminActivity1.class);
                startActivity(moveToAdmin1);
            }
        });
    }

    private void emailLogin() {
        progressBar.setVisibility(View.VISIBLE);

        String email, password;
        email = emailField.getText().toString();
        password = passwordField.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter your email!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter your password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent(LoginActivity.this, AdminViewActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Login failed! Please try again.", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void startUI() {
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);

        loginButton = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);
    }
}
