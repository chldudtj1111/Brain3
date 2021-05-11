package com.example.brain3.event;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.brain3.R;

public class Game extends Activity {
    String[] text1 = {"빨간색", "주황색", "노란색", "초록색", "파란색", "남색", "보라색"};
    String[] text2 = {"red", "orange", "yellow", "green", "blue", "navy", "purple"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colorgm);

        int rd = (int) (Math.random() * 7);
        int rd2 = (int) (Math.random() * 7);
        TextView text = (TextView) findViewById(R.id.textview);
        text.setText(text1[rd]);
        text.setTextColor(Color.parseColor(text2[rd2]));
    }

    public void EventBtn1(View v) {
        EditText editText = (EditText) findViewById(R.id.editTextTextMultiLine);
        String result = editText.getText().toString();

        if (result.equals(text2[0])) {
            finish();
        }
        else if (result.equals(text2[1])) {
            finish();
        }
        else if (result.equals(text2[2])) {
            finish();
        }
        else if (result.equals(text2[3])) {
            finish();
        }
        else if (result.equals(text2[4])) {
            finish();
        }
        else if (result.equals(text2[5])) {
            finish();
        }
        else if (result.equals(text2[6])) {
            finish();
        }
    }
}
//추가할미니게임