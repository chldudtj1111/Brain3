package com.example.brain3.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.view.WindowManager;
import android.widget.TextView;

import android.os.Handler;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.brain3.R;

import java.util.Random;

public class AlertReceiver extends Activity {

    public static Context context_main;
    public int rd;
    int count = 0;
    TextView text;
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            /* text = (TextView) findViewById(R.id.textView);*/
            count++;
            mHandler.removeMessages(0);
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabactivity);
        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setOnLongClickListener(new ImageView.OnLongClickListener() {
            public boolean onLongClick(View v) {
                mHandler.sendEmptyMessageDelayed(0, 100);
                return false;
            }
        });
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),notification);
        ringtone.setLooping(true);
        ringtone.play();

    }

    public void mOnclick(View v) {
        text = (TextView) findViewById(R.id.textView);
        count++;
        int imageResources[] = {R.drawable.du1, R.drawable.du2, R.drawable.du3, R.drawable.du4, R.drawable.du5};
        ImageView img = (ImageView) findViewById(R.id.imageView);
        Random ra = new Random();
        int a = ra.nextInt(5);
        int seed1 = ra.nextInt(10);

        int set_X = 1000;
        int set_Y = 1200;
        int set_raX = ra.nextInt(700)+330;
        int set_raY = ra.nextInt(900)+100;
        img.setX(set_X - set_raX);
        img.setY(set_Y - set_raY);
        img.setImageResource(imageResources[a]);
        mHandler.removeMessages(0);
        Random myr = new Random(seed1);
        rd = (int) myr.nextInt(20)+15;
        if(count > rd){
            rd = 35;
        }
        if (count == rd) {
            int input = 0;
            count = 0;
            Intent intent = new Intent(getApplicationContext(), AlertReceiver2.class);
            intent.putExtra("숫자", rd);
            context_main = this;
            startActivity(intent);
        }

    }
}