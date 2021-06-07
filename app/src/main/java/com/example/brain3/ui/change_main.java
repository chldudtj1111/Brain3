package com.example.brain3.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.brain3.R;
import com.example.brain3.for_list.AlarmUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.brain3.for_list.AddEditAlarmActivity.ADD_ALARM;
import static com.example.brain3.for_list.AddEditAlarmActivity.buildAddEditAlarmActivityIntent;

public class change_main extends AppCompatActivity {

    FloatingActionButton fab;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditAlarm.class);
                startActivity(intent);
            }
        });

}}
