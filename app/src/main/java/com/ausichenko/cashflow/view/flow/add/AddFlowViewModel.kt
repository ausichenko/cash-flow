package com.ausichenko.cashflow.view.flow.add

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ausichenko.cashflow.data.database.entites.CategoryEntity
import com.ausichenko.cashflow.data.database.entites.FlowEntity
import com.ausichenko.cashflow.data.models.Result
import com.ausichenko.cashflow.data.repository.CashRepository
import com.ausichenko.cashflow.utils.AppExecutors
import kotlinx.coroutines.experimental.launch
import java.util.*

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

    fun saveFlow(categoryId: Long, date: Date, name: String, description: String, price: Double) = launch(appExecutors.uiContext) {
        val flowEntity = FlowEntity(null, date, categoryId, name, description, price)
        cashRepository.saveFlow(flowEntity)
    }
}