package com.ausichenko.cashflow

import com.ausichenko.cashflow.data.repository.CashRepository
import com.ausichenko.cashflow.di.cashModule
import com.ausichenko.cashflow.view.NavigationViewModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest

class FTest : KoinTest {

    val viewModel : NavigationViewModel by inject()
    val repository : CashRepository by inject()

    @Before
    fun before(){
        startKoin(listOf(cashModule))
    }

    @After
    fun after(){
        closeKoin()
    }

    @Test
    fun testSayHello() {
        assertEquals(repository.giveHello(), viewModel.sayHello())
    }
}