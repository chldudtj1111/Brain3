package com.example.brain3.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;

import com.example.brain3.R;
public class AlertReceiver2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabactivity2);
    }
    public void EventBtn1(View v){
        Intent intent = new Intent(getApplicationContext(), AlertReceiver.class);
        int rd2 = ((AlertReceiver)AlertReceiver.context_main).rd;
        EditText editText = (EditText)findViewById(R.id.editTextNumber2);
        String text1 = editText.getText().toString();
        int result = Integer.parseInt(text1);
        if (result == rd2) {
            ActivityCompat.finishAffinity(AlertReceiver2.this);
            System.exit(0);
        } else
            startActivity(intent);

    }
}
