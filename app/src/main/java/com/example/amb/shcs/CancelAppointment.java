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
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CancelAppointment extends AppCompatActivity implements View.OnClickListener {

    TextView _checkText;

    MyUserDBHandler dbHandler;

    String userName;

    Button _delApptBtn;

    String[] delAppt = new String[100];
    int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_appointment);

        _delApptBtn = findViewById(R.id.delApptButton);

        _delApptBtn.setOnClickListener(this);

        Bundle intentData = getIntent().getExtras();
        userName = intentData.getString("userName");

        Toast.makeText(getBaseContext(), userName, Toast.LENGTH_SHORT).show();

        dbHandler = new MyUserDBHandler(this, null, null, 12);
        printDatabase();
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
                    delAppt[c] = checkArray[x];
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

        Toast.makeText(getBaseContext(), String.valueOf(c) + "Selected ", Toast.LENGTH_SHORT).show();

        for(int i=0; i<c ;i ++)
        {
            String text1 = delAppt[i].replace('\n', ' ');
            String text2 = text1.replace('-', ' ');
            String text3 = text2.trim();

            Pattern p = Pattern.compile("Doctor (.+) Date (.+) Time (.+)");
            String text = text3;
            Matcher m = p.matcher(text.trim());


            if (m.matches())
            {
                Toast.makeText(getBaseContext(), m.group(1) + m.group(2) + m.group(3), Toast.LENGTH_SHORT).show();

                String str1 = m.group(1);
                String str2 = m.group(2);
                String str3 = m.group(3);

                str1 = str1.trim();
                str2 = str2.trim();
                str3 = str3.trim();

                Toast.makeText(getBaseContext(), str1 + str2 + str3, Toast.LENGTH_SHORT).show();

                dbHandler.deleteAppt(userName, str1, str2, str3);
            }
            else
            {
                Toast.makeText(getBaseContext(), text + "String does not match", Toast.LENGTH_SHORT).show();
            }

        }

        Intent I = new Intent(this, ViewAppointments.class);
        I.putExtra("userName", userName);
        startActivity(I);

    }
}
