package com.example.amb.shcs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentTime extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    Button btnset;
    int day,month,year,hour,minute;
    int dayFinal,monthFinal,yearFinal,hourFinal,minuteFinal;
    String intentMessage;

    MyUserDBHandler dbHandler;

    String nameDoctor;

    String date, time;

    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment_time);

        Bundle _doctorType = getIntent().getExtras();
        intentMessage = _doctorType.getString("doctorType");
        userName = _doctorType.getString("userName");

        Toast.makeText(getBaseContext(), userName, Toast.LENGTH_SHORT).show();

        btnset = findViewById(R.id.setDT);

        btnset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookAppointmentTime.this,BookAppointmentTime.this,year,month,day);
                datePickerDialog.show();
            }
        });

        dbHandler = new MyUserDBHandler(this, null, null, 12);

        Toast.makeText(getBaseContext(), intentMessage , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1+1;
        dayFinal = i2;

        String yearF = String.valueOf(year);
        String monthF = String.valueOf(month);
        String dayF = String.valueOf(day);

        date = dayF + "/" + monthF + "/" + yearF;

        Toast.makeText(getBaseContext(), date , Toast.LENGTH_SHORT).show();

        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(BookAppointmentTime.this,BookAppointmentTime.this,hour,minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

        nameDoctor = dbHandler.findDoctor(intentMessage);

        Toast.makeText(getBaseContext(), nameDoctor , Toast.LENGTH_SHORT).show();

        String hourF = String.valueOf(hour);
        String minuteF = String.valueOf(minute);

        time  = hourF + ":" + minuteF;

        Toast.makeText(getBaseContext(), time , Toast.LENGTH_SHORT).show();


        hourFinal = i;
        minuteFinal = i1;

        dbHandler.addAppt(userName, nameDoctor, date, time);


        Intent intent = new Intent(BookAppointmentTime.this, AppointmentDetails.class);

        intent.putExtra("nameDoctor", nameDoctor);
        intent.putExtra("dateFinal", date);
        intent.putExtra("timeFinal", time);

        intent.putExtra("userName", userName);

        startActivity(intent);
    }
}
