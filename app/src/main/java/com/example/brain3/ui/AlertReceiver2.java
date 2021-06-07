package com.example.brain3.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brain3.R;

import java.util.Random;

public class AlertReceiver2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tabactivity2);
    }
    public void EventBtn1(View v) {
        Random myr = new Random(1);
        Intent intent = new Intent(getApplicationContext(), AlertReceiver.class);
        Intent intent2 = new Intent(getApplicationContext(), sendSMS.class);
        int rd2 = ((AlertReceiver) AlertReceiver.context_main).rd;
        EditText editText = (EditText) findViewById(R.id.editTextNumber2);
        String text1 = editText.getText().toString();
        int result = Integer.parseInt(text1);
        if (result == rd2) {
            startActivity(intent2);
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Toast.makeText(this, "뒤로가기 사용불가.", Toast.LENGTH_SHORT).show();
    }
}
