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

    @SuppressLint("ScheduleExactAlarm")
    private void setNotification() {
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        // إعداد الوقت للإشعار
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Log.d("SetNotification", "Notification set for: " + calendar.getTime());

        // الحصول على AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // إنشاء PendingIntent
        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // التحقق من AlarmManager وتعيين الإشعار
        if (alarmManager != null) {
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
}
