package com.example.solgikb.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    lateinit var binding: T

    /**
     * setContentView로 호출할 Layout의 리소스 Id.
     */
    abstract val layoutResId: Int

    /**
     * viewModel 로 쓰일 변수.
     */
    abstract val viewModel: V

    abstract val bindingVariable: Int

    /**
     * 레이아웃을 띄운 직후 호출.
     * 뷰나 액티비티의 속성 등을 초기화.
     * ex) 리사이클러뷰, 툴바, 드로어뷰..
     */
    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        initView()
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.setLifecycleOwner(this)
        binding.setVariable(bindingVariable, viewModel)
        binding.executePendingBindings()
    }

}