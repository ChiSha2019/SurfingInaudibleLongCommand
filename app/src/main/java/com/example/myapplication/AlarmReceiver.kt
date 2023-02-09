package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {
        context?.let { playAttackCommand(it)}
    }
}
