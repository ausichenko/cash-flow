package com.ausichenko.cashflow.di

import com.ausichenko.cashflow.data.repository.CashRepository
import com.ausichenko.cashflow.data.repository.CashRepositoryImpl
import com.ausichenko.cashflow.view.NavigationViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val cashModule : Module = module {
    viewModel { NavigationViewModel(get()) }
    single { CashRepositoryImpl() as CashRepository }
}

// todo: add room module