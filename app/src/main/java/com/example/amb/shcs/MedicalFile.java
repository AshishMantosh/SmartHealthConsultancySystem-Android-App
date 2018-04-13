package com.example.amb.shcs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MedicalFile extends AppCompatActivity {

    EditText _dobInput;
    EditText _sexInput;
    EditText _addressInput;
    EditText _contactInput;
    EditText _emergencyContactNameInput;
    EditText _emergencyContactInput;
    EditText _previousAilmentsInput;
    Button _completeButton;

    MyUserDBHandler dbHandler;

    String dob;
    String sex;
    String address;
    String phone;
    String emergencyContactName;
    String emergencyContact;
    String previousAilmentsInput;

    String intentMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_file);

        Bundle intentData = getIntent().getExtras();
        intentMessage = intentData.getString("userName");

        _dobInput = findViewById(R.id.dobInput);
        _sexInput = findViewById(R.id.sexInput);
        _addressInput = findViewById(R.id.addressInput);
        _contactInput = findViewById(R.id.contactInput);
        _emergencyContactNameInput = findViewById(R.id.emergencyContactNameInput);
        _emergencyContactInput = findViewById(R.id.emergencyContactInput);
        _completeButton = findViewById(R.id.completeButton);
        _previousAilmentsInput = findViewById(R.id.previousAilmentsInput);

        _completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complete();
            }
        });

        dbHandler = new MyUserDBHandler(this, null, null, 12);

    }

    public void complete() {

        dob = _dobInput.getText().toString();
        sex = _sexInput.getText().toString();
        address = _addressInput.getText().toString();
        phone = _contactInput.getText().toString();
        emergencyContactName = _emergencyContactNameInput.getText().toString();
        emergencyContact = _emergencyContactInput.getText().toString();
        previousAilmentsInput = _previousAilmentsInput.getText().toString();

        _completeButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(MedicalFile.this,
                R.style.MyTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressDialog.setMessage("Completing...");
        progressDialog.show();

        dbHandler.addMedicalFile(intentMessage, dob, sex, address, phone, emergencyContactName, emergencyContact, previousAilmentsInput);

        String status = "Inactive";
        String policy = "None";
        dbHandler.addUserIns(intentMessage, policy, status);

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
        _completeButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_SHORT).show();

        Intent I = new Intent(this, MainActivity.class);
        startActivity(I);

    }

}
