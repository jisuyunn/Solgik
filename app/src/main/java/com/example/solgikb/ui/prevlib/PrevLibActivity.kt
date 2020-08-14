package com.example.solgikb.ui.prevlib

import com.example.solgikb.BR
import com.example.solgikb.R
import com.example.solgikb.databinding.ActivityPrevLibBinding
import com.example.solgikb.ui.base.BaseActivity
import com.example.solgikb.utils.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class PrevLibActivity: BaseActivity<ActivityPrevLibBinding, PrevLibViewModel>() {
    override val layoutResId: Int get() = R.layout.activity_prev_lib
    override val viewModel: PrevLibViewModel by viewModel()
    override val bindingVariable: Int get() = BR.vm

    override fun initView() {
        viewModel.setInitData()
        binding.btnBack.setOnClickListener { this.finish() }
    }

    override fun observeChange() {

    }


}