package com.ausichenko.cashflow.view.categories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ausichenko.cashflow.data.database.entites.CategoryEntity
import com.ausichenko.cashflow.data.models.Result
import com.ausichenko.cashflow.data.repository.CashRepository
import com.ausichenko.cashflow.utils.AppExecutors
import kotlinx.coroutines.experimental.launch

class CategoryViewModel(
        private val appExecutors: AppExecutors,
        private val cashRepository: CashRepository
) : ViewModel() {

    val categories = MutableLiveData<List<CategoryEntity>>()

    fun getCategories() = launch(appExecutors.uiContext) {
        val result = cashRepository.getAllCategories()

        if (result is Result.Success) {
            categories.value = result.data
            Log.d("NavViewModel", "success")
        } else {
            Log.d("NavViewModel", "error")
        }
    }

    fun deleteCategory(categoryEntity: CategoryEntity) = launch(appExecutors.uiContext) {
        cashRepository.deleteCategory(categoryEntity)
    }
}