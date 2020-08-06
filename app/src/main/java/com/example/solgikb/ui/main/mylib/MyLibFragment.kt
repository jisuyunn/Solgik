package com.example.solgikb.ui.main.mylib

import android.content.Intent
import androidx.lifecycle.Observer
import com.example.solgikb.BR
import com.example.solgikb.R
import com.example.solgikb.data.model.Book
import com.example.solgikb.databinding.FragmentMyLibBinding
import com.example.solgikb.databinding.ItemBookBinding
import com.example.solgikb.ui.base.BaseFragment
import com.example.solgikb.ui.base.BaseRecyclerView
import com.example.solgikb.ui.bookdetail.BookDetailActivity
import com.example.solgikb.utils.INTENT_EXTRA_BOOK_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyLibFragment: BaseFragment<FragmentMyLibBinding, MyLibViewModel>() {
    override val layoutResId: Int get() = R.layout.fragment_my_lib
    override val viewModel: MyLibViewModel by viewModel()
    override val bindingVariable: Int get() = BR.vm

    override fun initView() {
        initData()
        initRecyclerView()
    }

    fun initData() {
        viewModel.initData()
        viewModel.checkLiveData.observe(viewLifecycleOwner, Observer {

        })
    }

    fun initRecyclerView() {
        binding.bookRv.adapter = object : BaseRecyclerView.Adapter<Book, ItemBookBinding>(
                layoutResId = R.layout.item_book,
                bindingVariableId = BR.book,
                itemClick = { item ->
                    val intent = object : Intent(context, BookDetailActivity::class.java) {}
                    intent.putExtra(INTENT_EXTRA_BOOK_ID, item.BId)
                    //startActivity(intent)
                }) {}
    }

}