package ru.spb.designedBy239School.advancedMusicPlayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mediaPlayer: MediaPlayer? =null



    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        var mediaPlayer = MediaPlayer.create(applicationContext,R.raw.ne_s_nachala_oxxymiron )
        startMusic.setOnClickListener{
            mediaPlayer.start()
        }

        pause.setOnClickListener {
            mediaPlayer!!.stop()
        }

    }

    override fun onPause() {
        super.onPause()
        mediaPlayer!!.stop()
        mediaPlayer!!.release()

    }

}
