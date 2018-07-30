package com.ausichenko.cashflow.di

import com.ausichenko.cashflow.data.repository.CashRepository
import com.ausichenko.cashflow.data.repository.CashRepositoryImpl
import com.ausichenko.cashflow.view.NavigationViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val cashModule : Module = applicationContext {
    viewModel { NavigationViewModel(get()) }
    single { CashRepositoryImpl() as CashRepository }
}