package com.ausichenko.cashflow.view.flow.add

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ausichenko.cashflow.data.database.entites.CategoryEntity
import com.ausichenko.cashflow.data.models.Result
import com.ausichenko.cashflow.data.repository.CashRepository
import com.ausichenko.cashflow.utils.AppExecutors
import kotlinx.coroutines.experimental.launch

class AddFlowViewModel(
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
}