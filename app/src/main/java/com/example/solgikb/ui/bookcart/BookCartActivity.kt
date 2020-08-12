package com.example.solgikb.ui.bookcart

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import com.example.solgikb.BR
import com.example.solgikb.R
import com.example.solgikb.databinding.ActivityBookCartBinding
import com.example.solgikb.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_book_cart.*
import kotlinx.android.synthetic.main.activity_book_cart.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class BookCartActivity: BaseActivity<ActivityBookCartBinding, BookCartViewModel>() {
    override val layoutResId: Int get() = R.layout.activity_book_cart
    override val viewModel: BookCartViewModel by viewModel()
    override val bindingVariable: Int get() = BR.vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnBack.setOnClickListener { view ->
            finish()
        }
    }


    override fun initView() {
        initTab()
    }

    fun initTab() {

    }

    override fun observeChange() { }

}