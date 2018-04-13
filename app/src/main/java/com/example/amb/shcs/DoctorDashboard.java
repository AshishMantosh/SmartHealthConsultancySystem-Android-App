package com.example.amb.shcs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DoctorDashboard extends AppCompatActivity {

    TextView _doctorRecord;

    MyUserDBHandler dbHandler;

    String doctorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        _doctorRecord = findViewById(R.id.doctorRecord);

        // You can be pretty confident that the intent will not be null here.
        Intent intentData = getIntent();

        // Get the extras (if there are any)
        Bundle extras = intentData.getExtras();
        if (extras != null) {

            doctorName = extras.getString("doctorName");

            // TODO: Do something with the value of

        }

        dbHandler = new MyUserDBHandler(this, null, null, 12);
        printDatabase();
    }

    //Print the database
    public void printDatabase(){
        String dbString = dbHandler.printDoctorAppt(doctorName);
        _doctorRecord.setText(dbString);

    }
}
