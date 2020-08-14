package com.example.solgikb.ui.main.home

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.solgikb.BR
import com.example.solgikb.R
import com.example.solgikb.data.model.Book
import com.example.solgikb.data.model.Check
import com.example.solgikb.data.model.Result
import com.example.solgikb.data.repository.IRepository
import com.example.solgikb.databinding.ItemBookBinding
import com.example.solgikb.ui.base.BaseRecyclerView
import com.example.solgikb.ui.base.BaseViewModel
import com.example.solgikb.ui.bookdetail.BookDetailActivity
import com.example.solgikb.utils.INTENT_EXTRA_BOOK_ID
import com.example.solgikb.utils.SingleLiveEvent

class HomeViewModel(application: Application, private val repo: IRepository): BaseViewModel() {

    val sle = SingleLiveEvent<String>()
    val searchbooktitle = MutableLiveData<String>()
    private val _bookLiveData = MutableLiveData<Int>()
    val bookLiveData = _bookLiveData.switchMap { i ->
        liveData {
            when(i) {
                0 -> {
                    val result = repo.getBookListByUId("-MDjV4gJl2BciZSSsuB1")
                    if(result is Result.Success) emit(result.data)
                }
                1 -> {
                    val result = repo.getBookListByTitle(searchbooktitle.value.toString())
                    if(result is Result.Success) emit(result.data)
                }
            }
        }
    }

    val recyclerAdapter = object : BaseRecyclerView.Adapter<Book, ItemBookBinding>(
            layoutResId = R.layout.item_book,
            bindingVariableId = BR.book,
            itemClick = { item ->
                val intent = object : Intent(application.applicationContext, BookDetailActivity::class.java) {}
                intent.putExtra(INTENT_EXTRA_BOOK_ID, item.BId)
                //startActivity(intent)
            }) {}

    fun recommendBookByUId(id: String) {
        _bookLiveData.postValue(0)
    }

    fun searchBookByTitle(title: String) {
        _bookLiveData.postValue(1)
    }

    fun onRecMain(){
        _bookLiveData.postValue(0)
        sle.value = ""
    }

    fun onClickSearch(){
        sle.value = searchbooktitle.value
        _bookLiveData.postValue(1)
    }

}