package com.ausichenko.cashflow.di

import androidx.room.Room
import com.ausichenko.cashflow.data.database.CashDatabase
import com.ausichenko.cashflow.data.repository.CashRepository
import com.ausichenko.cashflow.data.repository.CashRepositoryImpl
import com.ausichenko.cashflow.utils.AppExecutors
import com.ausichenko.cashflow.view.categories.CategoryViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val cashModule : Module = module {
    viewModel { CategoryViewModel(get(), get()) }
    single { CashRepositoryImpl(get(), get()) as CashRepository }
    single { AppExecutors() }
}

val roomModule : Module = module {
    single {
        Room.databaseBuilder(get(), CashDatabase::class.java, CashDatabase.databaseName).build()
    }

    single { get<CashDatabase>().categoryDao() }
    single { get<CashDatabase>().flowDao() }
}

val cashApp = listOf(cashModule, roomModule)