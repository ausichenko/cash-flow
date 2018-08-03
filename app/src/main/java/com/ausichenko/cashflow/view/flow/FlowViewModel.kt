package com.ausichenko.cashflow.view.flow

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ausichenko.cashflow.data.database.entites.FlowEntity
import com.ausichenko.cashflow.data.models.Result
import com.ausichenko.cashflow.data.repository.CashRepository
import com.ausichenko.cashflow.utils.AppExecutors
import kotlinx.coroutines.experimental.launch
import java.util.*

class FlowViewModel(
        private val appExecutors: AppExecutors,
        private val cashRepository: CashRepository
) : ViewModel() {

    val flow = MutableLiveData<List<FlowEntity>>()

    fun getFlow() = launch(appExecutors.uiContext) {
        val result = cashRepository.getAllFlow()

        if (result is Result.Success) {
            flow.value = result.data
            Log.d("FlowViewModel", "success")
        } else {
            Log.d("FlowViewModel", "error")
        }
    }

    fun saveFlowTest() = launch(appExecutors.uiContext) {
        val flowEntity = FlowEntity(null, Date(), null, "test", "test_desc", 1.0)
        cashRepository.saveFlow(flowEntity)
    }
}