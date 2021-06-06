package com.example.brain3.event;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.brain3.R;
import com.example.brain3.ui.sendSMS;

import java.util.Random;

public class Game extends Activity {
    public String[] text1 = {"빨간색", "주황색", "노란색", "초록색", "파란색", "남색", "보라색"};
    public String[] text2 = {"red", "orange", "yellow", "green", "blue", "navy", "purple"};
    public String color1;
    public int rd, rd2, rd3;
    public TextView text;
    public Uri notification;
    public Ringtone ringtone;
    public Random ran;
    public int count = 0;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colorgm);
        ran = new Random();
        rd = ran.nextInt(6);
        rd2 = ran.nextInt(6);
        rd3 = ran.nextInt(70)+30;
        color1 = text2[rd2];
        text = (TextView) findViewById(R.id.coltext);
        if(text.getText().equals(null)){
            text.setText(text1[rd]);
        }
        else {
            if (rd == rd2) {
                rd = ran.nextInt(6);
                rd2 = ran.nextInt(6);
            } else {
                text.setText(text1[rd]);
                text.setTextColor(Color.parseColor(color1));
                text.setTextSize(rd3);
            }
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(),notification);
        ringtone.setLooping(true);
        ringtone.play();
        System.out.println(text);
    }

    public void EventBtn1(View v) {
        EditText editText = (EditText) findViewById(R.id.editTextTextMultiLine);
        String result = editText.getText().toString();

        if (result.equals("빨간색") || result.equals("빨강") || result.equals("빨강색")){
            result = "red";
        }
        if (result.equals("주황색") || result.equals("주황")){
            result = "orange";
        }
        if (result.equals("노란색") || result.equals("노랑") || result.equals("노랑색")){
            result = "yellow";
        }
        if (result.equals("초록색") || result.equals("초록")){
            result = "green";
        }
        if (result.equals("파란색") || result.equals("파랑")|| result.equals("파랑색")){
            result = "blue";
        }
        if (result.equals("남색") || result.equals("남")){
            result = "navy";
        }
        if (result.equals("보라색") || result.equals("보라")){
            result = "purple";
        }

        Intent intent = getIntent();
        Intent intent2 = new Intent(getApplicationContext(), sendSMS.class);

        if (result.equals(color1)) {
            count ++;
        }
        if(count == 3)
            startActivity(intent2);
        editText.setText("");
        rd = ran.nextInt(6);
        rd2 = ran.nextInt(6);
        rd3 = ran.nextInt(70) + 30;
        color1 = text2[rd2];
        text.setText(text1[rd]);
        text.setTextColor(Color.parseColor(color1));
        text.setTextSize(rd3);


    }
}