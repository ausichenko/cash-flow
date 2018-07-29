package com.ausichenko.cashflow.view

import android.arch.lifecycle.ViewModel
import com.ausichenko.cashflow.data.repository.CashRepository

class NavigationViewModel(private val cashRepository: CashRepository) : ViewModel() {
    fun sayHello() = cashRepository.giveHello()
}