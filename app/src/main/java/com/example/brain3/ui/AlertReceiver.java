package com.example.brain3.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.widget.TextView;

import android.os.Handler;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
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

    }

    public void mOnclick(View v) {
        text = (TextView) findViewById(R.id.textView);
        count++;
        int imageResources[] = {R.drawable.du1, R.drawable.du2, R.drawable.du3, R.drawable.du4, R.drawable.du5};
        ImageView img = (ImageView) findViewById(R.id.imageView);
        Random ra = new Random();
        int a = ra.nextInt(5);
        img.setX((int) (Math.random() * 800));
        img.setY((int) (Math.random() * 800));
        img.setImageResource(imageResources[a]);
        mHandler.removeMessages(0);

        Random myr = new Random();
        rd = myr.nextInt(10);

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