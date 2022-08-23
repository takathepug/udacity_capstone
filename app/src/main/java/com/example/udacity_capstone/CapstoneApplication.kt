package com.example.udacity_capstone

import android.app.Application
import timber.log.Timber

class CapstoneApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}