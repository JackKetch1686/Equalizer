package ru.spb.designedBy239School.advancedMusicPlayer.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.util.Log

class BackgroundAudioService: Service() {
    var mediaPlayer = MediaPlayer()
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("SERVICE","Start")
        var mediaPlayer =MediaPlayer()
        mediaPlayer.stop()
        mediaPlayer.reset()
        mediaPlayer .setDataSource(this,Uri.parse("android.resource://ru.spb.designedBy239School.advancedMusicPlayer/raw/heavy"))
        mediaPlayer.prepare()
        mediaPlayer.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }





}