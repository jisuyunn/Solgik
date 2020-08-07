package com.example.solgikb.ui.main

import android.app.Application
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.solgikb.R
import com.example.solgikb.ui.base.BaseViewModel
import com.google.android.material.tabs.TabLayout


class MainViewModel(application: Application): BaseViewModel() {

    private val _adapter = MutableLiveData<ViewPagerAdapter>()
    val adapter: LiveData<ViewPagerAdapter> get() = _adapter

    val tabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {}

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            val textView = tab?.customView as TextView
            textView.textSize = 20.0f
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            if(tab?.customView == null) {
                val textView1 = TextView(application.applicationContext)
                val textView2 = TextView(application.applicationContext)
                textView1.textSize = 25.0f
                textView1.setTextColor(Color.BLACK)
                textView1.typeface = Typeface.DEFAULT_BOLD
                textView1.gravity = Gravity.BOTTOM
                textView1.text = application.applicationContext.getString(R.string.maintab1)
                tab?.parent?.getTabAt(0)?.customView = textView1
                textView2.textSize = 20.0f
                textView2.setTextColor(Color.BLACK)
                textView2.typeface = Typeface.DEFAULT_BOLD
                textView2.gravity = Gravity.BOTTOM
                textView2.text = application.applicationContext.getString(R.string.maintab2)
                tab?.parent?.getTabAt(1)?.customView = textView2
            } else {
                val textView = tab.customView as TextView
                textView.textSize = 25.0f
            }

        }

    }

    fun initTabs(adapter: ViewPagerAdapter) {
        _adapter.postValue(adapter)
    }
}