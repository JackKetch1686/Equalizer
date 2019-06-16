package ru.spb.designedBy239School.advancedMusicPlayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_player.*
import ru.spb.designedBy239School.advancedMusicPlayer.MainActivity

class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        var getIntentFromMainActivity=intent
        if (getIntentFromMainActivity.hasExtra("data_id")){
            CurrentSongNameText.text=getIntentFromMainActivity.getStringExtra("data_id")
        }
        BackToMainActivityFromPlayerActivity.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
        ToEqualizerActivity.setOnClickListener{
        startActivity(Intent(this, EqualizerActivity::class.java))}
    }
}
