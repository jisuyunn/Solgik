package com.example.solgikb.ui.main.mylib

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.solgikb.data.model.Book
import com.example.solgikb.data.model.Check
import com.example.solgikb.data.model.Result
import com.example.solgikb.data.model.User
import com.example.solgikb.data.repository.FirebaseRepository
import com.example.solgikb.ui.base.BaseViewModel
import com.example.solgikb.utils.calculatedDay


class MyLibViewModel(application: Application, private val repository: FirebaseRepository): BaseViewModel() {

    private val _userLiveData = MutableLiveData<String>()
    val userLiveData = _userLiveData.switchMap { id ->
        liveData(coroutineContext) {
            val result = repository.getUserById(id)
            if(result is Result.Success)
                emit(result.data)
        }
    }

    val check = MutableLiveData<Check>()
    val dDay = MutableLiveData<String>()
    private val _checkLiveData = MutableLiveData<String>()
    val checkLiveData = _checkLiveData.switchMap { id ->
        liveData(coroutineContext) {
            val result = repository.getCheckListByUId(id)
            if (result is Result.Success) {
                if(result.data.size > 0) {
                    check.postValue(result.data.get(0))
                    dDay.postValue(calculatedDay(result.data.get(0).expectDate))
                    val BId = mutableListOf<String>()
                    result.data.forEach { data ->
                        BId.add(data.BId)
                    }
                    _bookLiveData.postValue(BId)
                    emit(result.data)
                }
            }
        }
    }

    val checkTitle = MutableLiveData<String>()
    private val _bookLiveData = MutableLiveData<List<String>>()
    val bookLiveData = _bookLiveData.switchMap { id ->
        liveData(coroutineContext) {
            val result = repository.getBookListById(id)
            if (result is Result.Success) {
                var title: String? = ""
                if(result.data.size > 0) title = result.data.get(0).title
                if(result.data.size > 1) title += " 외 " + (result.data.size-1) + "권"
                checkTitle.postValue(title)
                emit(result.data)
            }
        }
    }


    fun getUserById(id: String) {
        _userLiveData.postValue(id)
    }

    fun getCheckListByUId(id: String) {
        _checkLiveData.postValue(id)
    }





    // Result 사용해야됨
//    val userLiveData = liveData<Result<User>>(coroutineContext) {
//        emit(Result.Loading)
//        val result = repository.getUser()
//        if(result is Result.Success)
//            emit(result)
//    }


}