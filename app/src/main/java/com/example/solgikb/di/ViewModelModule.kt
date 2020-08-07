package com.example.solgikb.di

import com.example.solgikb.ui.login.LoginViewModel
import com.example.solgikb.ui.main.MainViewModel
import com.example.solgikb.ui.main.home.HomeViewModel
import com.example.solgikb.ui.main.mylib.MyLibViewModel
import com.example.solgikb.ui.prevlib.PrevLibViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }

    viewModel {
        HomeViewModel(get(), get())
    }

    viewModel{
        MyLibViewModel(get(), get())
    }

    viewModel {
        LoginViewModel(get(), get())
    }

    viewModel {
        PrevLibViewModel(get(), get())
    }
}