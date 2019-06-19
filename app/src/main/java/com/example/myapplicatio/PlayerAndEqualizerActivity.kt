package com.example.myapplicatio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_player_and_equalizer.*

class PlayerAndEqualizerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_and_equalizer)

        val adapterOfPlayerAndEqualizerActivity = MainActivity.MyViewPagerAdapter(supportFragmentManager)
        adapterOfPlayerAndEqualizerActivity.addFragment(FragmentPlayer(), "Player ")
        adapterOfPlayerAndEqualizerActivity.addFragment(FragmentEqualizer(), "Equalizer ")



        vpOfPlayerAndEqualizerActivity.adapter=adapterOfPlayerAndEqualizerActivity
        tabsOfPlayerAndEqualizer.setupWithViewPager(vpOfPlayerAndEqualizerActivity)

    }
}
