package com.example.brain3.event;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

        int rd = (int) (Math.random() * 7);
        int rd2 = (int) (Math.random() * 7);
        color1 = text2[rd2];
        if (rd == rd2){
            rd = (int) (Math.random() * 7);
            rd2 = (int) (Math.random() * 7);
        }
        else {
            TextView text = (TextView) findViewById(R.id.coltext);
            text.setText(text1[rd]);
            text.setTextColor(Color.parseColor(color1));
        }
    }

    public void EventBtn1(View v) {
        EditText editText = (EditText) findViewById(R.id.editTextTextMultiLine);
        String result = editText.getText().toString();

        Intent intent = new Intent(getApplicationContext(), Game.class);
        if (result.equals(color1)) {
            ActivityCompat.finishAffinity(Game.this);
            System.exit(0);
        }

        else if (result.equals(color1)) {
            ActivityCompat.finishAffinity(Game.this);
            System.exit(0);
        }
        else if (result.equals(color1)) {
            ActivityCompat.finishAffinity(Game.this);
            System.exit(0);
        }

        else if (result.equals(color1)) {
            ActivityCompat.finishAffinity(Game.this);
            System.exit(0);
        }

        else if (result.equals(color1)) {
            ActivityCompat.finishAffinity(Game.this);
            System.exit(0);
        }
        else if (result.equals(color1)) {
            ActivityCompat.finishAffinity(Game.this);
            System.exit(0);
        }

        else if (result.equals(color1)) {
            ActivityCompat.finishAffinity(Game.this);
            System.exit(0);
        }
        else {
            startActivity(intent);
        }
    }
}
//추가할미니게임