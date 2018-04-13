package com.example.amb.shcs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InsuranceDetails extends AppCompatActivity {

    TextView _textView;

    MyUserDBHandler dbHandler;

    String userName;

    String status;

    Button _showStatus;
    Button _pickInsurance;
    TextView _goBackLink1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_details);

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

        _textView = findViewById(R.id.userInsDetails);
        _showStatus = findViewById(R.id.showStatusBtn);
        _pickInsurance = findViewById(R.id.pickInsuarnceBtn);
        _goBackLink1 = findViewById(R.id.goBack1);

        Toast.makeText(getBaseContext(), userName, Toast.LENGTH_SHORT).show();

        dbHandler = new MyUserDBHandler(this, null, null, 12);


        status = dbHandler.checkUserIns(userName);

        status = status.trim();
        Toast.makeText(getBaseContext(), status, Toast.LENGTH_SHORT).show();

        _showStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printDatabase();
            }
        });

        _pickInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent I = new Intent(InsuranceDetails.this, PickInsurance.class);
                I.putExtra("userName", userName);
                startActivity(I);

            }
        });

        _goBackLink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(InsuranceDetails.this, PatientDashboard.class);
                I.putExtra("userName", userName);
                startActivity(I);
            }
        });


//        printDatabase();
    }

    //Print the database
    public void printDatabase(){
        String dbString = dbHandler.insuranceDB(userName);
        _textView.setText(dbString);
    }
}
