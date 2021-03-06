package com.example.brain3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;
import com.example.brain3.R;
import java.util.Random;

public class Stepcount extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor stepCountSensor;
    TextView stepCountView;
    Random createRandom = new Random();
    int x = createRandom.nextInt(15)+10;

    // 현재 걸음 수
    int currentSteps = 0;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_main);

        stepCountView = findViewById(R.id.stepCountView);



        // 활동 퍼미션 체크
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){

            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }

        // 걸음 센서 연결
        // * 옵션
        // - TYPE_STEP_DETECTOR:  리턴 값이 무조건 1, 앱이 종료되면 다시 0부터 시작
        // - TYPE_STEP_COUNTER : 앱 종료와 관계없이 계속 기존의 값을 가지고 있다가 1씩 증가한 값을 리턴
        //
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        // 디바이스에 걸음 센서의 존재 여부 체크
        if (stepCountSensor == null) {
            Toast.makeText(this, "No Step Sensor", Toast.LENGTH_SHORT).show();
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_PLAY_SOUND);
        MediaPlayer player = MediaPlayer.create(this,R.raw.zz2);
        player.setLooping(true);
        player.start();




    }


    public void onStart() {
        super.onStart();
        if(stepCountSensor !=null) {
            // 센서 속도 설정
            // * 옵션
            // - SENSOR_DELAY_NORMAL: 20,000 초 딜레이
            // - SENSOR_DELAY_UI: 6,000 초 딜레이
            // - SENSOR_DELAY_GAME: 20,000 초 딜레이
            // - SENSOR_DELAY_FASTEST: 딜레이 없음
            //
            sensorManager.registerListener(this,stepCountSensor,SensorManager.SENSOR_DELAY_FASTEST);
        }
    }



    @Override
    public void onSensorChanged(SensorEvent event) {

        // 걸음 센서 이벤트 발생시
        if(event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR){

            if(event.values[0]==1.0f){
                // 센서 이벤트가 발생할때 마다 걸음수 증가
                currentSteps++;

                stepCountView.setText(String.valueOf(currentSteps));
                if(currentSteps==x){

                    Intent intent2 = new Intent(getApplicationContext(), sendSMS.class);
                    startActivity(intent2);
                    //ActivityCompat.finishAffinity(Stepcount.this);
                    //System.exit(0);
                    }
            }

        }

    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Toast.makeText(this, "뒤로가기 사용불가.", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}