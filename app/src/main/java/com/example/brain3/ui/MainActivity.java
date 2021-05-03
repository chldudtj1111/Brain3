package com.example.brain3.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import java.util.Random;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.brain3.R;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{
    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.layout_main);


        mTextView =  findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.button_timepicker);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }

        });

        Button buttonCancelAlarm = findViewById(R.id.button_cancel);
        buttonCancelAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                cancelAlarm();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);
    }

    private void updateTimeText(Calendar c){
        String timeText = "Alarm set for : ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        mTextView.setText(timeText);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void startAlarm(Calendar c){
        Random random = new Random();
        int a = random.nextInt(20);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        if(a/2==0){
        Intent intent = new Intent(this, Doshake.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, 0);

        if(c.before((Calendar.getInstance()))){
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);}
        else if(a/2!=0){
            Intent intent = new Intent(this, AlertReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, 0);

            if(c.before((Calendar.getInstance()))){
                c.add(Calendar.DATE, 1);
            }
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);}

        }
        //alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 1*60*1000 ,  pendingIntent);



    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Random random = new Random();
        int a = random.nextInt(20);
        if (a / 2 == 0) {
            Intent intent = new Intent(this, Doshake.class);


            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

            alarmManager.cancel(pendingIntent);
            mTextView.setText("Alarm canceled");
        } else if (a / 2 != 0) {
            Intent intent = new Intent(this, AlertReceiver.class);


            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

            alarmManager.cancel(pendingIntent);
            mTextView.setText("Alarm canceled");
        }
    }
}