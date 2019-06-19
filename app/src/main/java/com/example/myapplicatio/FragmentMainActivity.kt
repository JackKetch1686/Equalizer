package com.example.myapplicatio

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_fragment_main_activity.*

class FragmentMainActivity :Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fragment_main_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_fragment_main_activity.text="My Playlist"
        button_fragment_main_activity.setOnClickListener{
            startActivity(Intent(activity, PlayerAndEqualizerActivity::class.java))
        }
    }
}