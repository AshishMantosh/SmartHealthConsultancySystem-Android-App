package com.example.amb.shcs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PickInsurance extends AppCompatActivity implements View.OnClickListener {

    String userName;

    RadioButton ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8,ch9,ch10,ch11,ch12,ch13,ch14,ch15,ch16;
    Button btnNext;

    List list = new ArrayList();
    static int i=0;
    boolean flag=false;
    String type;

    MyUserDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_insurance);

        // You can be pretty confident that the intent will not be null here.
        Intent intentData = getIntent();

        // Get the extras (if there are any)
        Bundle extras = intentData.getExtras();
        if (extras != null) {

                userName = extras.getString("userName");

                // TODO: Do something with the value of

        }

        btnNext = findViewById(R.id.insNextButton);
        ch1 = findViewById(R.id.oneRB);
        ch2 = findViewById(R.id.twoRB);
        ch3 = findViewById(R.id.threeRB);
        ch4 = findViewById(R.id.fourRB);
        ch5 = findViewById(R.id.fiveRB);
        ch6 = findViewById(R.id.sixRB);
        ch7 = findViewById(R.id.sevenRB);
        ch8 = findViewById(R.id.eightRB);
        ch9 = findViewById(R.id.nineRB);
        ch10 = findViewById(R.id.tenRB);
        ch11 = findViewById(R.id.elevenRB);
        ch12 = findViewById(R.id.twelveRB);
        ch13 = findViewById(R.id.thirteenRB);
        ch14 = findViewById(R.id.fourteenRB);
        ch15 = findViewById(R.id.fifteenRB);
        ch16 = findViewById(R.id.sixteenRB);

        btnNext.setOnClickListener((View.OnClickListener) this);

        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch1.getTag());
                    flag=true;
                    type = "Aegon";
                }
            }
        });

        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch2.getTag());
                    flag=true;
                    type = "Aviva";
                }
            }
        });

        ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch3.getTag());
                    flag=true;
                    type = "Bajaj";
                }
            }
        });

        ch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch4.getTag());
                    flag=true;
                    type = "Birla";
                }
            }
        });

        ch5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch5.getTag());
                    flag=true;
                    type = "Exide";
                }
            }
        });

        ch6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch6.getTag());
                    flag=true;
                    type = "HDFC";
                }
            }
        });

        ch7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch7.getTag());
                    flag=true;
                    type = "ICICI";
                }
            }
        });

        ch8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch8.getTag());
                    flag=true;
                    type = "IDBI";
                }
            }
        });

        ch9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch9.getTag());
                    flag=true;
                    type = "IndiaFirst";
                }
            }
        });

        ch10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch10.getTag());
                    flag=true;
                    type = "Kotak";
                }
            }
        });

        ch11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch11.getTag());
                    flag=true;
                    type = "Max";
                }
            }
        });

        ch12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch12.getTag());
                    flag=true;
                    type = "PNB";
                }
            }
        });

        ch13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch13.getTag());
                    flag=true;
                    type = "Reliance";
                }
            }
        });

        ch14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch14.getTag());
                    flag=true;
                    type = "SBI";
                }
            }
        });

        ch15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch15.getTag());
                    flag=true;
                    type = "TATA";
                }
            }
        });

        ch16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    list.add(ch16.getTag());
                    flag=true;
                    type = "None";
                }
            }
        });

        dbHandler = new MyUserDBHandler(this, null, null, 12);
    }

    @Override
    public void onClick(View view) {

        if(flag == false)
        {
            Toast.makeText(PickInsurance.this,"No Insurance Policy Selected !",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(PickInsurance.this,"Chose " + type,Toast.LENGTH_SHORT).show();
            Intent I = new Intent(PickInsurance.this, InsuranceDetails.class);

            int id = dbHandler.findUserInsID(userName);
            String strID = String.valueOf(id);
            String status = "Active";
            if(type.equals("None"))
            {
                status = "Inactive";
            }
            dbHandler.updateInsurance(strID, userName, type, status);
            I.putExtra("userName", userName);
            startActivity(I);
        }
    }
}
