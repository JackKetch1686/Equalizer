package ru.spb.designedBy239School.advancedMusicPlayer

import android.media.audiofx.Equalizer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
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

        val equalizer = Equalizer(0, intent.getStringExtra("Session_Id").toInt())
        equalizer.enabled = true

        if (equalizer.enabled || equalizer.hasControl()) Log.d("EQ",
            "equalizer is enable and has control")

        Log.d("EQ", "Start level is ${equalizer.getBandLevel(0)}")

        text_preset.text = equalizer.getPresetName(equalizer.currentPreset)
        text_number_of_bands.text = equalizer.numberOfBands.toString()
        tex_min_freq.text = equalizer.getBandFreqRange(0)[0].toString()
        tex_min_freq.text = equalizer.getBandFreqRange(0)[1].toString()


        val bars = arrayOf(eq_seek_bar_0, eq_seek_bar_1, eq_seek_bar_2, eq_seek_bar_3, eq_seek_bar_4)
        for(bar in bars) {
            bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar : SeekBar?) {
                    val bandNumber = bar.id.toString()[bar.id.toString().length-1].toShort()
                    equalizer.setBandLevel(bandNumber, seekBar!!.progress.toShort())
                    text_band_0.text = equalizer.getBandLevel(bandNumber).toString()
                }
            })
        }
//        val bands = Array<Short>(5) { i->i.toShort() }
//        for (band in bands) {
//            Log.d("EQ",("${equalizer.getBandFreqRange(band)[0]} ${equalizer.getBandFreqRange(band)[1]}"))
//        }
    }

    override fun onStop() {
        super.onStop()

    }
}
