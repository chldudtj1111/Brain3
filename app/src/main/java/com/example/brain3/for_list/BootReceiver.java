package com.example.brain3.for_list;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.brain3.for_list.DatabaseHelper;
import com.example.brain3.for_list.Alarm;

import java.util.List;
import java.util.concurrent.Executors;

import static android.content.Intent.ACTION_BOOT_COMPLETED;
import static com.example.brain3.for_list.AlarmReceiver.setReminderAlarms;

/**
 * Re-schedules all stored alarms. This is necessary as {@link AlarmManager} does not persist alarms
 * between reboots.
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Executors.newSingleThreadExecutor().execute(() -> {
                final List<Alarm> alarms = DatabaseHelper.getInstance(context).getAlarms();
                setReminderAlarms(context, alarms);
            });
        }
    }

}
