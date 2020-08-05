package com.example.solgikb.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solgikb.di.viewModelModule
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers

open class BaseViewModel : ViewModel() {

    /**
     * RxJava 의 observing을 위한 부분
     */
    private val compositeDisposable = CompositeDisposable()
    protected val coroutineContext = viewModelScope.coroutineContext + Dispatchers.IO

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}