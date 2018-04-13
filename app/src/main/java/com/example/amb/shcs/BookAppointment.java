package com.example.amb.shcs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.assist.AssistStructure;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookAppointment extends AppCompatActivity implements View.OnClickListener {

    RadioButton ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8,ch9,ch10,ch11,ch12,ch13,ch14,ch15;
    Button btnNext;

    List list = new ArrayList();
    static int i=0;
    boolean flag=false;
    String type;

    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        Bundle intentData = getIntent().getExtras();
        userName = intentData.getString("userName");

        Toast.makeText(getBaseContext(), userName, Toast.LENGTH_SHORT).show();

        btnNext = findViewById(R.id.nextButton);
        ch1 = findViewById(R.id.cardiologistRB);
        ch2 = findViewById(R.id.dermatologistRB);
        ch3 = findViewById(R.id.diabetologistRB);
        ch4 = findViewById(R.id.entSpecialistRB);
        ch5 = findViewById(R.id.endocrinologistRB);
        ch6 = findViewById(R.id.gastroenterologistRB);
        ch7 = findViewById(R.id.gynaecologistRB);
        ch8 = findViewById(R.id.neurologistRB);
        ch9 = findViewById(R.id.obstetricianRB);
        ch10 = findViewById(R.id.oncologistRB);
        ch11 = findViewById(R.id.ophthalmologistRB);
        ch12 = findViewById(R.id.orthopaedistRB);
        ch13 = findViewById(R.id.psychiatristRB);
        ch14 = findViewById(R.id.pulmonologistRB);
        ch15 = findViewById(R.id.urologistRB);

        btnNext.setOnClickListener((View.OnClickListener) this);

        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch1.getTag());
                    flag=true;
                    type = "Cardiologist";
                }
            }
        });

        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch2.getTag());
                    flag=true;
                    type = "Dermatologist";
                }
            }
        });

        ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch3.getTag());
                    flag=true;
                    type = "Diabetologist";
                }
            }
        });

        ch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch4.getTag());
                    flag=true;
                    type = "EntSpecialist";
                }
            }
        });

        ch5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch5.getTag());
                    flag=true;
                    type = "Endocrinologist";
                }
            }
        });

        ch6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch6.getTag());
                    flag=true;
                    type = "Gastroenterologist";
                }
            }
        });

        ch7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch7.getTag());
                    flag=true;
                    type = "Gynaecologist";
                }
            }
        });

        ch8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch8.getTag());
                    flag=true;
                    type = "Neurologist";
                }
            }
        });

        ch9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch9.getTag());
                    flag=true;
                    type = "Obstetrician";
                }
            }
        });

        ch10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch10.getTag());
                    flag=true;
                    type = "Oncologist";
                }
            }
        });

        ch11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch11.getTag());
                    flag=true;
                    type = "Ophthalmologist";
                }
            }
        });

        ch12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch12.getTag());
                    flag=true;
                    type = "Orthopaedist";
                }
            }
        });

        ch13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch13.getTag());
                    flag=true;
                    type = "Psychiatrist";
                }
            }
        });

        ch14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch14.getTag());
                    flag=true;
                    type = "Pulmonologist";
                }
            }
        });

        ch15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch15.getTag());
                    flag=true;
                    type = "Urologist";
                }
            }
        });


    }


    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.nextButton) {
            if(flag == false)
            {
                Toast.makeText(BookAppointment.this,"No Specialist Selected !",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(BookAppointment.this,"Chose " + type,Toast.LENGTH_SHORT).show();
                Intent I = new Intent(this, BookAppointmentTime.class);
                I.putExtra("doctorType", type);
                I.putExtra("userName", userName);
                startActivity(I);
            }
        }

    }
}
