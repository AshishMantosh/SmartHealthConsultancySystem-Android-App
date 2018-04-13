package com.example.amb.shcs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewAppointments extends AppCompatActivity {

    TextView _textView;

    MyUserDBHandler dbHandler;

    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments);

        // You can be pretty confident that the intent will not be null here.
        Intent intentData = getIntent();

        // Get the extras (if there are any)
        Bundle extras = intentData.getExtras();
        if (extras != null) {
            if (extras.containsKey("userName")) {
                userName = extras.getString("userName");

                // TODO: Do something with the value of
            }
        }

        _textView = findViewById(R.id.userAppt);

        Toast.makeText(getBaseContext(), userName, Toast.LENGTH_SHORT).show();

        dbHandler = new MyUserDBHandler(this, null, null, 12);
        printDatabase();
    }

    //Print the database
    public void printDatabase(){
        String dbString = dbHandler.appointmentDatabase(userName);
        _textView.setText(dbString);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ViewAppointments.this, PatientDashboard.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }
}
