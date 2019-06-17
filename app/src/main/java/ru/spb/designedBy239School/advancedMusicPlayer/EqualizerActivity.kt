package ru.spb.designedBy239School.advancedMusicPlayer

import android.media.audiofx.BassBoost
import android.media.audiofx.Equalizer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_equalizer.*

class EqualizerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equalizer)

        if (intent.hasExtra("Session_Id")) {
            Log.d("EQ", "has Extra " + intent.getStringExtra("Session_Id"))
            text_view.text = intent.getCharSequenceExtra("Session_Id")
        }

        val bassboost = BassBoost(0, intent.getStringExtra("Session_Id").toInt())

        eq_seek_bar_BASS.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                bassboost.setStrength(seekBar.progress.toShort())
                Log.d("EQ", "BASS is ${bassboost.properties.strength}")
            }
        })


        val equalizer = Equalizer(1, intent.getStringExtra("Session_Id").toInt())
        equalizer.enabled = true

        if (equalizer.enabled && equalizer.hasControl()) Log.d(
            "EQ",
            "equalizer is enable and has control"
        )

        Log.d("EQ", "Start level is ${equalizer.getBandLevel(0)}")

        text_preset.text = equalizer.getPresetName(equalizer.currentPreset)
        text_number_of_bands.text = equalizer.numberOfBands.toString()

        eq_seek_bar_0.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
                equalizer.enabled = true
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                equalizer.setBandLevel(0, seekBar.progress.toShort())
                Log.d("EQ", "BAnd 0 level is ${equalizer.getBandLevel(0)}")
            }
        })

        eq_seek_bar_1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                equalizer.setBandLevel(1, seekBar.progress.toShort())
                Log.d("EQ", "BAnd 1 level is ${equalizer.getBandLevel(0)}")
            }
        })

        eq_seek_bar_2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                equalizer.setBandLevel(2, seekBar.progress.toShort())
                Log.d("EQ", "BAnd 2 level is ${equalizer.getBandLevel(2)}")
            }
        })

        eq_seek_bar_3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                equalizer.setBandLevel(3, seekBar.progress.toShort())
                Log.d("EQ", "BAnd 3 level is ${equalizer.getBandLevel(3)}")
            }
        })

        eq_seek_bar_4.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                equalizer.setBandLevel(4, seekBar.progress.toShort())
                Log.d("EQ", "BAnd 4 level is ${equalizer.getBandLevel(4)}")
            }
        })

        preset_1.setOnClickListener {
            equalizer.usePreset(1)
            Log.d("EQ", "Preset 1")
        }

        preset_2.setOnClickListener {
            equalizer.usePreset(2)
            Log.d("EQ", "Preset 2")
        }


    }

}
