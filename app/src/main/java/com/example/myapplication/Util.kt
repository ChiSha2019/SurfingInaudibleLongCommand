package com.example.myapplication

import android.content.Context
import android.media.MediaPlayer

fun playAttackCommand(context: Context) {
    val mp = MediaPlayer.create(context, R.raw.sound)
    mp.start()
    mp.setVolume(1F,1F)
}