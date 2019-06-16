package ru.spb.designedBy239School.advancedMusicPlayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_equalizer.*

class EqualizerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equalizer)
        BackToPlayerActivity.setOnClickListener{
            startActivity(Intent(this, PlayerActivity::class.java))
        }
    }
}
