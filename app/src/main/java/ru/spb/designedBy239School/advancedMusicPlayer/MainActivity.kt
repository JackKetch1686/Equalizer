package ru.spb.designedBy239School.advancedMusicPlayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mediaPlayer: MediaPlayer? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MY_DEBUG","onCreate active")


        var mediaPlayer = MediaPlayer.create(applicationContext,R.raw.ne_s_nachala_oxxymiron )
        startMusic.setOnClickListener{
            mediaPlayer!!.start()
        }
        pause.setOnClickListener {
            mediaPlayer!!.stop()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MY_DEBUG","onStart active")
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer!!.stop()
        mediaPlayer!!.release()

    }

}
