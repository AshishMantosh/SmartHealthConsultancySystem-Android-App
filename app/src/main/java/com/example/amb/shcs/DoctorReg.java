package com.example.amb.shcs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorReg extends AppCompatActivity {

    EditText _dobDocInput;
    EditText _sexDocInput;
    EditText _addressDocInput;
    EditText _contactDocInput;
    EditText _specialtyInput;
    EditText _experienceInput;
    EditText _qualificationInput;
    Button _completeDocButton;

    MyUserDBHandler dbHandler;

    String dob;
    String sex;
    String address;
    String phone;
    String specialty;
    String experience;
    String qualification;

    String intentMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_reg);

        Bundle intentData = getIntent().getExtras();
        intentMessage = intentData.getString("DoctorName");

        _dobDocInput = findViewById(R.id.dobDocInput);
        _sexDocInput = findViewById(R.id.sexDocInput);
        _addressDocInput = findViewById(R.id.addressDocInput);
        _contactDocInput = findViewById(R.id.contactDocInput);
        _specialtyInput = findViewById(R.id.specialtyInput);
        _experienceInput = findViewById(R.id.experienceInput);
        _completeDocButton = findViewById(R.id.completeDocButton);
        _qualificationInput = findViewById(R.id.qualificationInput);

        _completeDocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complete();
            }
        });

        dbHandler = new MyUserDBHandler(this, null, null, 11);
    }

    public void complete(){

        dob = _dobDocInput.getText().toString();
        sex = _sexDocInput.getText().toString();
        address = _addressDocInput.getText().toString();
        phone = _contactDocInput.getText().toString();
        specialty = _specialtyInput.getText().toString();
        experience = _experienceInput.getText().toString();
        qualification = _qualificationInput.getText().toString();

        _completeDocButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(DoctorReg.this,
                R.style.MyTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressDialog.setMessage("Completing...");
        progressDialog.show();

        dbHandler.addDocReg(intentMessage, dob, sex, address, phone, specialty, experience, qualification);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onComplete();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onComplete() {
        _completeDocButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_SHORT).show();

        Intent I = new Intent(this, MainActivity.class);
        startActivity(I);

    }
}
