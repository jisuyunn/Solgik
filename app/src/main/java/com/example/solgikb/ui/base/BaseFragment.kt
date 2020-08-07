package com.example.solgikb.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

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

    abstract fun observeChange()
    //private var isSetBackButtonValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setLifecycleOwner(this)
        binding.setVariable(bindingVariable, viewModel)
        binding.executePendingBindings()
        initView()
        observeChange()
    }

}