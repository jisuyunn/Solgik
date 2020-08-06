package com.example.solgikb.di

import com.example.solgikb.data.repository.IRepository
import com.example.solgikb.data.repository.RepositoryImpl
import com.example.solgikb.data.source.FirebaseSource
import com.example.solgikb.data.source.PreferenceSource
import org.koin.dsl.module


val dataModule = module {
    single {
        provideRepository(get(), get())
    }

    single {
        FirebaseSource()
    }

    single {
        PreferenceSource(get())
    }
}

private fun provideRepository(
        firebaseSource: FirebaseSource,
        preferenceSource: PreferenceSource
): IRepository {
    return RepositoryImpl(
        db = firebaseSource,
        pref = preferenceSource
    )
}
