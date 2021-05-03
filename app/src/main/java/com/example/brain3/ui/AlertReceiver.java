package com.example.brain3.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import com.example.brain3.R;

import java.util.Random;

public class AlertReceiver extends AppCompatActivity {

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
        int imageResources[] = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.end};
        ImageView img = (ImageView) findViewById(R.id.imageView);

        img.setX((int) (Math.random() * 800));
        img.setY((int) (Math.random() * 800));

        mHandler.removeMessages(0);
        int min = 15;
        int max = 40;
        Random myr = new Random();
        rd = myr.nextInt(max-min +1) + min;

        if (count == (int) rd * 40 / 100)
            img.setImageResource(imageResources[2]);

        if (count == (int) rd * 70 / 100)
            img.setImageResource(imageResources[3]);


        if (count == rd) {
            int input = 0;
            count = 0;
            Intent intent = new Intent(getApplicationContext(), AlertReceiver2.class);
            intent.putExtra("숫자", rd);
            context_main = this;
            startActivity(intent);
            img.setImageResource(imageResources[4]);
        }
    }

}