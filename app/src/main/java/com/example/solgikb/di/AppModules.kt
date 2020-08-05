package com.example.solgikb.di

import com.example.solgikb.data.DataManager
import com.example.solgikb.data.repository.FirebaseRepository
import com.example.solgikb.ui.main.MainViewModel
import com.example.solgikb.ui.main.home.HomeViewModel
import com.example.solgikb.ui.main.mylib.MyLibViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        MainViewModel(get(), get())
    }
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel{
        MyLibViewModel(get(), get())
    }
}

val apiModule = module {
    single {
        FirebaseRepository()
    }
}

val appModules = listOf(viewModelModule, apiModule)