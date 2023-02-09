package com.example.myapplication

import android.content.Context
import android.media.MediaPlayer
import androidx.work.Worker
import androidx.work.WorkerParameters

class AttackWorker(
    private val ctx: Context,
    params: WorkerParameters
) : Worker(ctx, params) {
    lateinit var myPlayer: MediaPlayer
    override fun doWork(): Result {
        myPlayer = MediaPlayer.create(ctx, R.raw.sound)
        myPlayer.start()
        myPlayer.setVolume(1F,1F)
        return Result.success()
    }
}