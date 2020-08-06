package com.example.solgikb.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.solgikb.R
import com.example.solgikb.ui.main.home.HomeFragment
import com.example.solgikb.ui.main.mylib.MyLibFragment
import java.util.*

class ViewPagerAdapter(fm: FragmentManager,
                       private val fragments: List<Fragment>) :  FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = 2

}