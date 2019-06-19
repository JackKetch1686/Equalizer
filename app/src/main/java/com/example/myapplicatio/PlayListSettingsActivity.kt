package com.example.myapplicatio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_play_list_settings.*

class PlayListSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_list_settings)
        toMainActivity.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
