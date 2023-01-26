package com.example.myapplication

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var mp: MediaPlayer
    private lateinit var playButton: Button
    private lateinit var stopButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mp = MediaPlayer.create(this, R.raw.sound)
        playButton = findViewById(R.id.playButton)
        playButton.setOnClickListener {
            mp.start()
            mp.setVolume(0.1F,0.1F)
        }

        stopButton = findViewById(R.id.stopButton)
        stopButton.setOnClickListener {
            mp.stop()
        }
    }

    override fun onResume() {
        super.onResume()
    }
}