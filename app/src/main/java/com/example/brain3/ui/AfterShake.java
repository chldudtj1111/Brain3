package com.example.brain3.ui;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;

import com.example.brain3.R;

public class AfterShake extends Activity {

   public static  Random a = new Random();
    TextView text1;
    public static int b = a.nextInt(4)+1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_shake);











        text1 = (TextView) findViewById(R.id.textView6);

        text1.setText("" + b +"번째 색깔은 무엇인가요?");
    }

    public void Button123(View view)
    {
        Intent intent = getIntent();
        ArrayList<String> data = (ArrayList<String>) intent.getSerializableExtra("ArrayList");
        Serializable s = intent.getSerializableExtra("ArrayList");


        Intent intent2 = new Intent(getApplicationContext(), sendSMS.class);
        EditText editText = (EditText)findViewById(R.id.editTextNumber5);
        String text22 = editText.getText().toString();


        if(data.get(b-1).equals(text22) ){
            startActivity(intent2);
        }

        else{
            finish();}
        }




    }
