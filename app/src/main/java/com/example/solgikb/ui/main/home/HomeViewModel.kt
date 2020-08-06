package com.example.solgikb.ui.main.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.solgikb.data.model.Result
import com.example.solgikb.data.repository.IRepository
import com.example.solgikb.ui.base.BaseViewModel

class HomeViewModel(application: Application, private val repo: IRepository): BaseViewModel() {

    private val _bookLiveData = MutableLiveData<String>()
    val bookLiveData = _bookLiveData.switchMap { id ->
        liveData(coroutineContext) {
            val result = repo.getBookListByUId(id)
            if (result is Result.Success) {
                emit(result.data)
            }
        }
    }

    fun recommendBookByUId(id: String) {
        _bookLiveData.postValue(id)
    }
}