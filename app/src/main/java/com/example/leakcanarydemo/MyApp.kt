package com.example.leakcanarydemo

import android.app.Application
import leakcanary.LeakCanary

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}