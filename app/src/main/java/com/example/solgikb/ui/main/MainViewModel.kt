package com.example.solgikb.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.solgikb.data.model.Book
import com.example.solgikb.data.model.Check
import com.example.solgikb.data.model.User
import com.example.solgikb.data.repository.FirebaseRepository
import com.example.solgikb.ui.base.BaseViewModel
import com.google.android.material.tabs.TabLayout


class MainViewModel(application: Application, private val repository: FirebaseRepository): BaseViewModel() {

    private val _bookLiveData = MutableLiveData<User>()
    val bookLiveData: LiveData<User> get() = _bookLiveData

    private val _adapter = MutableLiveData<ViewPagerAdapter>()
    val adapter: LiveData<ViewPagerAdapter> get() = _adapter

    var tabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {}

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
        }

    }

    fun initTabs(adapter: ViewPagerAdapter) {
        _adapter.postValue(adapter)
    }
}