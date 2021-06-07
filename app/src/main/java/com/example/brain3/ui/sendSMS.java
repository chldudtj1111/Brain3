package com.example.brain3.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.brain3.R;

public class sendSMS extends Activity {
    Context mContext;
    EditText smsNumber, smsTextContext;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        mContext = this;

        smsNumber = (EditText) findViewById(R.id.smsNumber);
        smsTextContext = (EditText) findViewById(R.id.smsText);
        send = findViewById(R.id.sendBtn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(sendSMS.this, Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                    Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent2);
                }else{
                    ActivityCompat.requestPermissions(sendSMS.this,new String[]{Manifest.permission.SEND_SMS},100);
                }
            }
        });
    }

    public void sendSMS(){
        String smsNum = smsNumber.getText().toString();
        String smsText = smsTextContext.getText().toString();

        if (smsNum.length()>0 && smsText.length()>0){
            SmsManager mSmsManager = SmsManager.getDefault();
            mSmsManager.sendTextMessage(smsNum, null, smsText, null, null);
            Toast.makeText(getApplicationContext(), "전송 완료", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "모두 입력해 주세요", Toast.LENGTH_SHORT).show();
        }
        ;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100&&grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            sendSMS();
        }else{
            Toast.makeText(getApplicationContext(),"Permission Denied!",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Toast.makeText(this, "뒤로가기 사용불가.", Toast.LENGTH_SHORT).show();
    }
}


//약속기능 예약문자.
