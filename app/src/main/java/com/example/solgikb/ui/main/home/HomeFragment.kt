package com.example.solgikb.ui.main.home

import android.content.Intent
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.solgikb.BR
import com.example.solgikb.R
import com.example.solgikb.data.model.Book
import com.example.solgikb.databinding.FragmentHomeBinding
import com.example.solgikb.databinding.ItemBookBinding
import com.example.solgikb.ui.base.BaseFragment
import com.example.solgikb.ui.base.BaseRecyclerView
import com.example.solgikb.ui.bookdetail.BookDetailActivity
import com.example.solgikb.utils.INTENT_EXTRA_BOOK_ID
import kotlinx.android.synthetic.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutResId: Int get() = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModel()
    override val bindingVariable: Int get() = BR.vm

    override fun initView() {
        initData()
        initRecyclerView()
    }

    fun initData() {
        viewModel.recommendBookByUId("")
    }

    fun initRecyclerView() {
        binding.bookRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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