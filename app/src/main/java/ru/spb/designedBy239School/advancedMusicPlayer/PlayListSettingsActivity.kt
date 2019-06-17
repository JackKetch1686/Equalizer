package ru.spb.designedBy239School.advancedMusicPlayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_play_list_settings.*

class PlayListSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_list_settings)
        val listOfSettings = Array(50) { x -> "Setting $x" }
        val adapterOfSettings = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfSettings)
        Log.d("MYMUSIC", "before adapter ")

        backToPlayListActivity.setOnClickListener{
            startActivity(Intent(this, PlaylistActivity::class.java))
        }

        PlayListSettingsListView.adapter = adapterOfSettings
        Log.d("MYMUSIC", "after adapter ")
        PlayListSettingsListView.setOnItemClickListener { parent, view, position, id ->
            (view as TextView).text = "clicked"
        }
    }
}
