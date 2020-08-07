package com.example.solgikb.ui.main.home

import android.app.Application
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.solgikb.BR
import com.example.solgikb.R
import com.example.solgikb.data.model.Book
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
    private val _bookLiveData = MutableLiveData<String>()
    val bookLiveData = _bookLiveData.switchMap { id ->
        liveData(coroutineContext) {
            val result = repo.getBookListByUId(id)
            if (result is Result.Success) {
                emit(result.data)
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
        _bookLiveData.postValue(id)
    }

    fun searchBookByTitle(title: String) {
        _bookLiveData.postValue(title)
    }

    fun onClickSearch(){
        sle.value = searchbooktitle.value
    }

}