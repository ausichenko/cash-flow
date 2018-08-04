package com.ausichenko.cashflow.view.categories.add

import androidx.lifecycle.ViewModel
import com.ausichenko.cashflow.data.database.entites.CategoryEntity
import com.ausichenko.cashflow.data.repository.CashRepository
import com.ausichenko.cashflow.utils.AppExecutors
import kotlinx.coroutines.experimental.launch

class AddCategoryViewModel(
        private val appExecutors: AppExecutors,
        private val cashRepository: CashRepository
) : ViewModel() {

    fun saveCategory(name: String) = launch(appExecutors.uiContext) {
        val categoryEntity = CategoryEntity(null, name)
        cashRepository.saveCategory(categoryEntity)
    }
}