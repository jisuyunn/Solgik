package com.example.solgikb

import android.app.Application
import com.example.solgikb.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {

    override fun onCreate() {
        super.onCreate()
//        AppLogger.init()
//        AndroidNetworking.initialize(applicationContext)
//        if (BuildConfig.DEBUG) {
//            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)
//        }
        // start Koin!
        startKoin {
            // Android context
            androidContext(this@Application)
            // modules
            modules(appModules)
        }
    }
}