package com.example.solgikb.ui.main

import androidx.fragment.app.FragmentManager
import com.example.solgikb.R
import com.example.solgikb.BR
import com.example.solgikb.data.model.Book
import com.example.solgikb.data.model.Check
import com.example.solgikb.data.model.User
import com.example.solgikb.databinding.ActivityMainBinding
import com.example.solgikb.ui.base.BaseActivity
import com.example.solgikb.ui.main.home.HomeFragment
import com.example.solgikb.ui.main.mylib.MyLibFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResId: Int get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()
    override val bindingVariable: Int get() = BR.vm


    override fun initView() {
        viewModel.initTabs(ViewPagerAdapter(supportFragmentManager,
                listOf(getString(R.string.maintab1), getString(R.string.maintab2)),
                listOf(HomeFragment(), MyLibFragment())))
    }

}