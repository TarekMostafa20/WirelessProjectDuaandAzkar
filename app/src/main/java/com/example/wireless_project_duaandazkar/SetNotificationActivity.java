package com.example.wireless_project_duaandazkar;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SetNotificationActivity extends AppCompatActivity {

    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_notification);

        timePicker = findViewById(R.id.timePicker);
        Button btnSetNotification = findViewById(R.id.btnSetNotification);

        btnSetNotification.setOnClickListener(v -> setNotification());
    }

    private boolean isExactAlarmPermissionGranted() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            return alarmManager != null && alarmManager.canScheduleExactAlarms();
        }
        return true;
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private void setNotification() {
        if (!isExactAlarmPermissionGranted()) {
            Toast.makeText(this, "Exact alarm permission required. Enable it in app settings.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
            startActivity(intent);
            return;
        }

        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Log.d("SetNotification", "Notification set for: " + calendar.getTime());

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        if (alarmManager != null) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this,
                    0,
                    new Intent(this, NotificationReceiver.class),
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        pendingIntent
                );
            } else {
                alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        pendingIntent
                );
            }

            Toast.makeText(this, "Notification set for " + hour + ":" + minute, Toast.LENGTH_SHORT).show();
        } else {
            Log.e("SetNotification", "AlarmManager is null!");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!isExactAlarmPermissionGranted()) {
                Toast.makeText(this, "Permission still not granted. Please enable exact alarms.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
