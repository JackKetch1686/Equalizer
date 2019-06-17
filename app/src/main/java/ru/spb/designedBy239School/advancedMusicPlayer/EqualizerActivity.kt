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

        val equalizer = Equalizer(0, intent.getStringExtra("Session_Id").toInt())
        equalizer.enabled = true

        if (equalizer.enabled && equalizer.hasControl()) Log.d(
            "EQ",
            "equalizer is enable and has control"
        )

        Log.d("EQ", "Start level is ${equalizer.getBandLevel(0)}")
        Log.d(
            "EQ", "lowest level is ${equalizer.bandLevelRange[0]} " +
                    "and highest is ${equalizer.bandLevelRange[1]}"
        )

        text_preset.text = equalizer.getPresetName(equalizer.currentPreset)
        text_number_of_bands.text = equalizer.numberOfBands.toString()
        tex_min_freq.text = equalizer.getBandFreqRange(0)[0].toString()
        tex_min_freq.text = equalizer.getBandFreqRange(0)[1].toString()

        eq_seek_bar_0.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                equalizer.setBandLevel(0, seekBar.progress.toShort())
                Log.d("EQ", "BAnd level is ${equalizer.getBandLevel(0)}")
            }
        })

        eq_seek_bar_1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                equalizer.setBandLevel(1, seekBar.progress.toShort())
                Log.d("EQ", "BAnd level is ${equalizer.getBandLevel(0)}")
            }
        })

        eq_seek_bar_2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                equalizer.setBandLevel(2, seekBar.progress.toShort())
                Log.d("EQ", "BAnd level is ${equalizer.getBandLevel(2)}")
            }
        })

        eq_seek_bar_3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                equalizer.setBandLevel(3, seekBar.progress.toShort())
                Log.d("EQ", "BAnd level is ${equalizer.getBandLevel(3)}")
            }
        })

        eq_seek_bar_4.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                equalizer.setBandLevel(4, seekBar.progress.toShort())
                Log.d("EQ", "BAnd level is ${equalizer.getBandLevel(4)}")
            }
        })


    }

}