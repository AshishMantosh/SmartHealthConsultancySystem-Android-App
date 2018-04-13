package com.example.amb.shcs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AppointmentDetails extends AppCompatActivity {

    String intentMsg1, intentMsg2, intentMsg3, intentMsg4;
    TextView _textView;

    String appointment;

    TextView _goBackLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);


       Bundle intentData = getIntent().getExtras();
        intentMsg1 = intentData.getString("nameDoctor");
        intentMsg2 = intentData.getString("dateFinal");
        intentMsg3 = intentData.getString("timeFinal");
        intentMsg4 = intentData.getString("userName");

        appointment = "Doctor - " + intentMsg1 + "\n Date - " + intentMsg2 + "\n Time - " + intentMsg3 ;

        _textView = findViewById(R.id.userAppt);
        _textView.setText(appointment);
        _goBackLink = findViewById(R.id.goBack);

        _goBackLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppointmentDetails.this, PatientDashboard.class);
                intent.putExtra("userName", intentMsg4);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AppointmentDetails.this, PatientDashboard.class);
        intent.putExtra("userName", intentMsg4);
        startActivity(intent);
    }
}
