package com.example.brain3.event;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.brain3.R;

public class Game extends Activity {
    String[] text1 = {"빨간색", "주황색", "노란색", "초록색", "파란색", "남색", "보라색"};
    String[] text2 = {"red", "orange", "yellow", "green", "blue", "navy", "purple"};
    public String color1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colorgm);

        int rd = (int) (Math.random() * 6);
        int rd2 = (int) (Math.random() * 6);
        int rd3 = (int) (Math.random()*90)+20;
        color1 = text2[rd2];

        if (rd == rd2){
            rd = (int) (Math.random() * 7);
            rd2 = (int) (Math.random() * 7);
        }
        else {
            TextView text = (TextView) findViewById(R.id.coltext);
            text.setText(text1[rd]);
            text.setTextColor(Color.parseColor(color1));
            text.setTextSize(rd3);
        }
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),notification);
        ringtone.play();
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

        Intent intent = new Intent(getApplicationContext(), Game.class);
        Intent intent2 = new Intent(getApplicationContext(), Message.class);
        if (result.equals(color1)) {
            startActivity(intent2);
            //ActivityCompat.finishAffinity(Game.this);
            //System.exit(0);
        }
        else {
            startActivity(intent);
        }
    }
}
//추가할미니게임