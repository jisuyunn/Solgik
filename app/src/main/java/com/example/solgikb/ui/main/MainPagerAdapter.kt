package com.example.solgikb.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.ArrayList

class MainPagerAdapter(fm: FragmentManager) :  FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT ) {

    private val fragmentList = ArrayList<Fragment>()
    private val titleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titleList[position]
    }

    fun add(index: Int, fragment: Fragment, title: String) {
        fragmentList.add(index, fragment)
        titleList.add(index, title)
    }

    fun clear() {
        fragmentList.clear()
        titleList.clear()
    }
}