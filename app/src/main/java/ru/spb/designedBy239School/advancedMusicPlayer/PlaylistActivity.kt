package ru.spb.designedBy239School.advancedMusicPlayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_play_list.*
import ru.spb.designedBy239School.advancedMusicPlayer.MainActivity

class PlaylistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_list)

        goToPlayListSettingsActivity.setOnClickListener{
            val intentOfSettings= Intent(this,PlayListSettingsActivity::class.java)
            startActivity(intentOfSettings)
        }
        ToMainActivity.setOnClickListener{
           startActivity(Intent(this, MainActivity::class.java))
        }
        val listOfMusic= Array(50){x->"Song $x"}                                   //S O N G S
        PlayListListView.adapter=ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfMusic)
        PlayListListView.setOnItemClickListener{parent, view, position, id->
            (view as TextView).text="clicked"
        }
    }
}
