package com.ausichenko.cashflow

import android.app.Application
import com.ausichenko.cashflow.di.cashApp
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startKoin

class CashApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        startKoin(this, cashApp)
    }
}