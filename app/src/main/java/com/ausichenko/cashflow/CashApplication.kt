package com.ausichenko.cashflow

import android.app.Application
import com.ausichenko.cashflow.di.cashApp
import org.koin.android.ext.android.startKoin

class CashApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, cashApp)
    }
}