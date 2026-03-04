package com.montfel.fipe.androidapp

import android.app.Application
import com.montfel.fipe.di.initKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
