package com.example.brain3.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.brain3.R;
import com.example.brain3.event.Game;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{
    private TextView mTextView;
    public int rd1;
    public static ArrayList<String> alarmlist = new ArrayList<>();
    public  static  ArrayList<Calendar> timelist = new ArrayList<>();
    long resTime = System.currentTimeMillis();
    SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
    Date currentTime = new Date ( );
    String dTime = formatter.format ( currentTime );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_alarm);
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


        int x=0;
        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);


        updateTimeText(c);
        timelist.add(c);
        startAlarm(timelist.get(x));
        x++;

    }




    private void updateTimeText(Calendar c){
        String timeText = "Alarm set for : ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        ListView lv = (ListView)findViewById(R.id.mlist);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, alarmlist);

        alarmlist.add(DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime()));
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        mTextView.setText(timeText);
        Toast.makeText(this,(DateFormat.getTimeInstance(DateFormat.SHORT)
                        .format(c.getTime()))+"에 울립니다",
                Toast.LENGTH_LONG)    // 보여줄 기간 (길게, 짧게)
                .show();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void startAlarm(Calendar c) {
        Random random = new Random();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //rd1 = random.nextInt(4)+1;
        rd1=3;
        if (rd1 == 1) {
            Intent intent = new Intent(this, Game.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, 0);
            if (c.before(Calendar.getInstance())) {//this line compares the time set to the current time
                c.add(Calendar.DATE, 1);//the alarm will be set for next day now
            }

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);


        }
        if (rd1 == 2) {
            Intent intent = new Intent(this, Doshake.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, 0);

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

        }
        if (rd1 == 3) {
            Intent intent = new Intent(this, AlertReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, 0);
            if (c.before(Calendar.getInstance())) {//this line compares the time set to the current time
                c.add(Calendar.DATE, 1);//the alarm will be set for next day now
            }
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

        }
        if (rd1 == 4) {
            Intent intent = new Intent(this, Stepcount.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, 0);
            if (c.before(Calendar.getInstance())) {//this line compares the time set to the current time
                c.add(Calendar.DATE, 1);//the alarm will be set for next day now
            }
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

        }
        if (c.before((Calendar.getInstance()))) {
            c.add(Calendar.DATE, 1);
        }

    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //Random random = new Random();
        rd1=2;
        if (rd1 == 1) {
            Intent intent = new Intent(this, Game.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
            alarmManager.cancel(pendingIntent);
            mTextView.setText("Alarm canceled");
            alarmlist.clear();
        }
        if (rd1 == 2) {
            Intent intent = new Intent(this, Doshake.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
            alarmManager.cancel(pendingIntent);
            mTextView.setText("Alarm canceled");
            alarmlist.clear();
        }
        if (rd1 == 3) {
            Intent intent = new Intent(this, AlertReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
            alarmManager.cancel(pendingIntent);
            mTextView.setText("Alarm canceled");
            alarmlist.clear();
        }
        if (rd1 == 4) {
            Intent intent = new Intent(this, Stepcount.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
            alarmManager.cancel(pendingIntent);
            mTextView.setText("Alarm canceled");
            alarmlist.clear();
        }

    }
}