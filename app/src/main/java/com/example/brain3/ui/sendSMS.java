package com.example.brain3.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.brain3.R;

public class sendSMS extends Activity {
    Context mContext;
    EditText smsNumber, smsTextContext;
    Button send;
    ImageButton call;

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

                }else{
                    ActivityCompat.requestPermissions(sendSMS.this,new String[]{Manifest.permission.SEND_SMS},100);
                }
            }
        });
        call = findViewById(R.id.imageButton2);
        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==RESULT_OK)
        {
            Cursor cursor = getContentResolver().query(data.getData(),
                    new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER},null,null,null);
            cursor.moveToFirst();

            smsNumber.setText(cursor.getString(1));
            cursor.close();
        }
        super.onActivityResult(requestCode,resultCode,data);
    }



    public void sendSMS(){

        String smsNum = smsNumber.getText().toString();
        String smsText = smsTextContext.getText().toString();

        if (smsNum.length()>0 && smsText.length()>0){
            SmsManager mSmsManager = SmsManager.getDefault();
            mSmsManager.sendTextMessage(smsNum, null, smsText, null, null);
            Toast.makeText(getApplicationContext(), "전송 완료", Toast.LENGTH_LONG).show();
            moveTaskToBack(true);
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }else{
            Toast.makeText(this, "모두 입력해 주세요", Toast.LENGTH_SHORT).show();
        }
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
