package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast


class AlarmService: Service() {
    lateinit var myPlayer: MediaPlayer
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        Toast.makeText(this, "Service Successfully Created", Toast.LENGTH_SHORT).show()
        myPlayer = MediaPlayer.create(this, R.raw.sound)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Attack begins in 5 seconds even with your phone is locked", Toast.LENGTH_SHORT).show()
        myPlayer.start()
        myPlayer.setVolume(1F,1F)
        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "Service Stopped and Music Stopped", Toast.LENGTH_LONG).show()
    }
}