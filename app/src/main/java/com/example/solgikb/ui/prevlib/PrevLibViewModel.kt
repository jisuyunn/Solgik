package com.example.solgikb.ui.prevlib

import android.app.Application
import android.content.Intent
import android.widget.RadioGroup
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

class PrevLibViewModel(application: Application, private val repo: IRepository): BaseViewModel() {

    private val _bookLiveData = MutableLiveData<Int>()
    val bookLiveData = _bookLiveData.switchMap { i ->
        liveData {
            when(i) {
                0,1,2 -> {
                    val result = repo.getBookListByUId("-MDjV4gJl2BciZSSsuB1")
                    if(result is Result.Success) emit(result.data)
                }
            }
        }
    }

    val radioCheckListener = RadioGroup.OnCheckedChangeListener { group, id ->
        if(group.checkedRadioButtonId == id) {
            when(id) {
                R.id.week_filter -> _bookLiveData.postValue(0)
                R.id.month_filter -> _bookLiveData.postValue(1)
                R.id.three_month_filter -> _bookLiveData.postValue(2)
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

}