package com.example.solgikb.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.solgikb.data.repository.FirebaseRepository
import com.example.solgikb.ui.base.BaseViewModel


class MainViewModel(application: Application, private val data: FirebaseRepository): BaseViewModel() {

    private val _bookLiveData = MutableLiveData<String>()
    val bookLiveData: LiveData<String> get() = _bookLiveData

}