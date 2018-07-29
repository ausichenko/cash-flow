package com.ausichenko.cashflow.di

import com.ausichenko.cashflow.data.repository.CashRepository
import com.ausichenko.cashflow.data.repository.CashRepositoryImpl
import com.ausichenko.cashflow.view.NavigationViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val cashModule : Module = applicationContext {
    viewModel { NavigationViewModel(get()) }
    bean { CashRepositoryImpl() as CashRepository }
}