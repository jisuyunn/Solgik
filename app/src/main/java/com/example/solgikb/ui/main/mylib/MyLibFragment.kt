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
import com.example.solgikb.ui.prevlib.PrevLibActivity
import com.example.solgikb.ui.prevlib.PrevLibViewModel
import com.example.solgikb.utils.INTENT_EXTRA_BOOK_ID
import com.example.solgikb.utils.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyLibFragment: BaseFragment<FragmentMyLibBinding, MyLibViewModel>() {
    override val layoutResId: Int get() = R.layout.fragment_my_lib
    override val viewModel: MyLibViewModel by viewModel()
    override val bindingVariable: Int get() = BR.vm

    override fun initView() {
        initData()
        initRecyclerView()
        binding.btn.setOnClickListener { v ->
            startActivity(Intent(activity, PrevLibActivity::class.java))
        }
    }

    override fun observeChange() {
        observe(viewModel.checkLiveData) {}
    }

    fun initData() {
        viewModel.initData()
    }

    fun initRecyclerView() {

    }


}