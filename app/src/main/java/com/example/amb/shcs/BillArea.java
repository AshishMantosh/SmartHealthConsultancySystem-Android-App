package com.example.amb.shcs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class BillArea extends AppCompatActivity implements View.OnClickListener {

    MyUserDBHandler dbHandler;

    String userName;

    Button _proceedPayBtn;

    String[] payBill = new String[100];
    int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_area);

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

        _proceedPayBtn = findViewById(R.id.proceedPayButton);

        _proceedPayBtn.setOnClickListener(this);

        Toast.makeText(getBaseContext(), userName, Toast.LENGTH_SHORT).show();

        dbHandler = new MyUserDBHandler(this, null, null, 12);
//        printDatabase();
    }

    //Print the database
    public void printDatabase(){

        int count = dbHandler.getCountApptDatabase(userName);
        String dbString;

        final String[] checkArray = new String[count];
        CheckBox[] cb = new CheckBox[count];



        Toast.makeText(getBaseContext(), String.valueOf(count), Toast.LENGTH_SHORT).show();

        for (int i = 0; i< count; i++)
        {
            dbString = dbHandler.printApptDBperLine(userName, i);
            checkArray[i] = dbString;
        }

        LinearLayout my_layout = findViewById(R.id.layDB);

        for (int i = 0; i < count; i++)
        {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setId(i);
            checkBox.setText(checkArray[i]);

            final int x = i;

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    payBill[c] = checkArray[x];
                    c++;
                }
            });

            LinearLayout.LayoutParams checkParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            checkParams.setMargins(10, 10, 10, 10);
            checkParams.gravity = Gravity.CENTER;

            my_layout.addView(checkBox, checkParams);
        }

    }

    @Override
    public void onClick(View view) {


    }
}
