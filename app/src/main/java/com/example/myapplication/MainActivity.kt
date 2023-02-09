package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager


class MainActivity : AppCompatActivity() {
    private lateinit var mp: MediaPlayer
    private lateinit var alarmManagerWithBroadCastReceiver: Button
    private lateinit var alarmManagerWithService: Button
    private lateinit var justService: Button
    private lateinit var workManagerButton: Button
    private lateinit var volumeEditText: EditText
    private lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mp = MediaPlayer.create(this, R.raw.attackcommand)
        //playSoundAndSetVolume(0.05F)


        volumeEditText = findViewById(R.id.volumeEditText)
        alarmManagerWithBroadCastReceiver = findViewById(R.id.setAlarmManagerWithBroadCastReceiver)

        alarmManagerWithBroadCastReceiver.setOnClickListener {
            alarmManagerWithBR(this)
        }

        alarmManagerWithService = findViewById(R.id.alarmManagerWithService)
        alarmManagerWithService.setOnClickListener {
            alarmManagerWithService(this)
        }

        justService = findViewById(R.id.justService)
        justService.setOnClickListener {
            justService(this)
        }

        workManagerButton = findViewById(R.id.workManager)
        workManagerButton.setOnClickListener {
            startWorkManager(this)
        }
    }

    private fun startWorkManager(context: Context) {
        val workManager: WorkManager = WorkManager.getInstance(context)

        val constraints = Constraints.Builder()
            .setRequiresDeviceIdle(true)
            .setRequiresCharging(true)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<AttackWorker>()
            .setConstraints(constraints)
            .build()

        workManager.enqueue(workRequest)
    }
    private fun alarmManagerWithService(context: Context) {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmService::class.java)
        val pendingIntent = PendingIntent.getService(context, 0, intent, 0)
        val triggerTime = System.currentTimeMillis() + 5000

//        val triggerTime = Calendar.getInstance()
//        triggerTime[Calendar.HOUR_OF_DAY] = 2
//        triggerTime[Calendar.MINUTE] = 0
//        triggerTime[Calendar.SECOND] = 0


        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
    }

    private fun alarmManagerWithBR(context: Context) {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        val triggerTime = System.currentTimeMillis() + 5000
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
    }

    private fun justService(context: Context) {
        val intent = Intent(context, AlarmService::class.java)
        startService(intent)
    }
}