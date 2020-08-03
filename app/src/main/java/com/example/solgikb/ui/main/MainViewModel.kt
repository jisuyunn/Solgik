package com.example.solgikb.ui.main

import android.app.Application
import android.content.ContentValues.TAG
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.solgikb.data.model.Result
import com.example.solgikb.data.model.User
import com.example.solgikb.data.repository.FirebaseRepository
import com.example.solgikb.di.viewModelModule
import com.example.solgikb.ui.base.BaseViewModel


class MainViewModel(application: Application, private val data: FirebaseRepository): BaseViewModel() {

    private val _bookLiveData = MutableLiveData<User>()
    val bookLiveData: LiveData<User> get() = _bookLiveData

    fun insertUser(user: User) {
        data.insertUser(user)
    }
}