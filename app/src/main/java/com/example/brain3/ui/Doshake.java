package com.example.brain3.ui;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.example.brain3.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Parcelable;
import android.os.Vibrator;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
public class Doshake extends Activity {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    public static final int sub = 1001;
    public static Context context_main;
    LinearLayout layout;
    private static  ArrayList<String> colors = new ArrayList<>();

    private static Random ra = new Random();
    private static int a= ra.nextInt(3);

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.changecolor);

        layout=findViewById(R.id.layout);
        layout.setBackgroundColor(Color.WHITE);
        context_main = this;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();


        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                /*
                 * The following method, "handleShakeEvent(count):" is a stub //
                 * method you would use to setup whatever you want done once the
                 * device has been shook.
                 */
                int red,blue,green=0;
                Random ra = new Random();
                int a = ra.nextInt(4)+1;
                int colorcount=0;

                if(a==1){
                layout.setBackgroundColor(Color.RED);
                    colors.add("빨간색");
                }
                else if(a==2){
                    layout.setBackgroundColor(Color.GREEN);
                    colors.add("초록색");}
                else if(a==3){
                    layout.setBackgroundColor(Color.BLUE);
                    colors.add("파란색");}
                else{
                    layout.setBackgroundColor(Color.YELLOW);
                    colors.add("노란색");}
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(500);

                }

        });
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);


        MediaPlayer player = MediaPlayer.create(this,R.raw.zz1);
        player.setLooping(true);
        player.start();
    }

    public static void goToAfterShakeActivity(){

        Intent intent = new Intent(context_main, AfterShake.class);
        intent.putExtra("숫자", a);

        intent.putStringArrayListExtra("ArrayList", colors  );


        context_main.startActivity(intent);
    }

    public static void goTosendSMSActivity(){
        Intent intent = new Intent(context_main, sendSMS.class);
        context_main.startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);

    }

    // background 상황에서도 흔들림을 감지하고 적용할 필요는 없다
    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);
        Toast.makeText(this, "메뉴 사용불가.", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    public static class ShakeDetector
            // 센서 이벤트를 들을 listener를 충족하는 class
            implements SensorEventListener {

        /*
         * The gForce that is necessary to register as shake.
         * Must be greater than 1G (one earth gravity unit).
         * You can install "G-Force", by Blake La Pierre
         * from the Google Play Store and run it to see how
         *  many G's it takes to register a shake
         */
        // 중력, 중력가속도을 기준으로 삼아서 진동, 움직임을 측정한다.
        // 흔들림 감지할 때 기준이 되는 가해지는 힘
        private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
        // 흔들림 감지할때 최소 0.5초를 기준으로 측정한다.
        private static final int SHAKE_SLOP_TIME_MS = 500;
        // 흔드는 횟수는 3초마다 초기화
        private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;
        // listener
        private OnShakeListener mListener;
        // 시간 기록용
        private long mShakeTimestamp;
        // 횟수
        private int mShakeCount;
        private View view;


        // listener setting
        public void setOnShakeListener(OnShakeListener listener) {
            this.mListener = listener;
        }

        // listener interface
        public interface OnShakeListener {
            public void onShake(int count);
        }

        // 정확도가 변할 때? 사용하지 않는다
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // ignore
        }

        // 센서 method.
        @Override
        public void onSensorChanged(SensorEvent event) {

            if (mListener != null) {
                // x,y,z 축의 값을 받아온다
                Random random = new Random();
                //int a = random.nextInt(10) + 1;

                int a = 15;   //화면전환이 성공적인지 알아볼려고 15라는값줫음

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                // 중력 가속도값으로 나눈 값으로 만든다
                float gX = x / SensorManager.GRAVITY_EARTH;
                float gY = y / SensorManager.GRAVITY_EARTH;
                float gZ = z / SensorManager.GRAVITY_EARTH;
                // gforce는 중력가속도를 포함하는 물체가 받는 힘
                // 1일때는 평소에 받는 중력(정지)
                // 1이하일때(아래로 떨어지며 힘을 덜받을 때)
                // 1이상일 때(위로 올라가면서 힘을 더 받을 때)
                // 단순히 힘의 크기를 계산하기 때문에 피타고라스로 구한다
                // gForce will be close to 1 when there is no movement.
                float gForce = (float) Math.sqrt(gX * gX + gY * gY + gZ * gZ);
                // 진동을 감지했을 때
                // gforce가 기준치 이상일 경우
                if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                    final long now = System.currentTimeMillis();
                    // 진동 간격이 너무 짧을 때는 무시
                    // ignore shake events too close to each other (500ms)
                    if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                        return;
                    }
                    // 3초 이상 걸렸을 때 reset한다
                    // reset the shake count after 3 seconds of no shakes
                    if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                        mShakeCount = 0;
                    }
                    // 업데이트한다
                    mShakeTimestamp = now;
                    mShakeCount++;
                    // 흔들렸을 때 행동을 설정한다

                    mListener.onShake(mShakeCount);

                    if (mShakeCount == 10) {
                        goToAfterShakeActivity();
                        //android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }
            }

        }
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Toast.makeText(this, "뒤로가기 사용불가.", Toast.LENGTH_SHORT).show();
    }
}