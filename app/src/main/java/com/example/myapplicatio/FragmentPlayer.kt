package com.example.myapplicatio

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_fragment_main_activity.*
import kotlinx.android.synthetic.main.fragment_fragment_player.*

class FragmentPlayer :Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //tv_fragment_main_activity.text="Player"
        backToMainActivity.setOnClickListener{
            startActivity(Intent(activity, MainActivity::class.java))
        }
    }
}