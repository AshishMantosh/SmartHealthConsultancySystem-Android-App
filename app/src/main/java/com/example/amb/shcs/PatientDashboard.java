package com.example.amb.shcs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientDashboard extends AppCompatActivity {

    Button _viewAppt;
    Button _patBookAppt;
    Button _patCancelAppt;
//    Button _billPayment;
    Button _insuranceInfo;
    Button _logout;
    Button _changePassword;

    String userName;
    String email;
    String checkType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);


        // You can be pretty confident that the intent will not be null here.
        Intent intentData = getIntent();

        // Get the extras (if there are any)
        Bundle extras = intentData.getExtras();
        if (extras != null) {

                userName = extras.getString("userName");
                email = extras.getString("email");
                checkType = extras.getString("checkType");
                // TODO: Do something with the value of

        }


        _viewAppt = findViewById(R.id.viewAppt);
        _patBookAppt = findViewById(R.id.patBookAppt);
        _patCancelAppt = findViewById(R.id.patCancelAppt);
//        _billPayment = findViewById(R.id.billPaymentBtn);
        _logout = findViewById(R.id.logout);
        _changePassword = findViewById(R.id.changePassword);
        _insuranceInfo = findViewById(R.id.insuranceInfoBtn);

        _viewAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAppointment();
            }
        });

        _patBookAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookAppointment();
            }
        });

        _patCancelAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAppointment();
            }
        });

/*        _billPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billPayment();
            }
        });
*/
        _insuranceInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInsuranceInfo();
            }
        });

        _logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(PatientDashboard.this, MainActivity.class);
                startActivity(I);
            }
        });

        _changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });

    }


    private void viewAppointment() {
        Intent I = new Intent(PatientDashboard.this, ViewAppointments.class);
        I.putExtra("userName", userName);
        startActivity(I);
    }

    public void bookAppointment(){
        Intent I = new Intent(PatientDashboard.this, BookAppointment.class);
        I.putExtra("userName", userName);
        startActivity(I);
    }

    private void cancelAppointment() {
        Intent I = new Intent(PatientDashboard.this, CancelAppointment.class);
        I.putExtra("userName", userName);
        startActivity(I);
    }


    private void billPayment() {
        Intent I = new Intent(PatientDashboard.this, BillArea.class);
        I.putExtra("userName", userName);
        startActivity(I);

    }


    private void showInsuranceInfo() {
        Intent I = new Intent(PatientDashboard.this, InsuranceDetails.class);
        I.putExtra("userName", userName);
        startActivity(I);
    }

    private void changePassword() {
        Intent I = new Intent(PatientDashboard.this, ChangePassword.class);
        I.putExtra("userName", userName);
        I.putExtra("email", email);
        I.putExtra("checkType", checkType);
        startActivity(I);
    }
}
