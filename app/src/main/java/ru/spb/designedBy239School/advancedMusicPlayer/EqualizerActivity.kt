package ru.spb.designedBy239School.advancedMusicPlayer

import android.media.audiofx.Equalizer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_equalizer.*
import kotlinx.android.synthetic.main.activity_main.*

class EqualizerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equalizer)

        if(intent.hasExtra("Session_Id")) {
            Log.d("EQ", "has Extra " + intent.getStringExtra("Session_Id"))
            text_view.text = intent.getCharSequenceExtra("Session_Id")
        }

        val equalizer = Equalizer(1, intent.getStringExtra("Session_Id").toInt())

        text_preset.text = equalizer.getPresetName(equalizer.currentPreset)
        text_number_of_bands.text = equalizer.numberOfBands.toString()
    }
}
