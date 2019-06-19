package com.example.myapplicatio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapterOfMainActivity = MyViewPagerAdapter(supportFragmentManager)
        adapterOfMainActivity.addFragment(FragmentMainActivity(), "My Playlist ")
        adapterOfMainActivity.addFragment(FragmentPlaylist(), "Playlist ")

        vpOfMainActivity.adapter=adapterOfMainActivity
        tabsOfMainActivity.setupWithViewPager(vpOfMainActivity)

    }


    class MyViewPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager){
        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]

        }
        fun addFragment(fragment: Fragment, title: String){
            fragmentList.add(fragment)
            titleList.add(title)
        }


        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

    }
}
