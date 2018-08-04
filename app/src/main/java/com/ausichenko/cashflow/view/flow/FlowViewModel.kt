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
        private val cashRepository: CashRepository) : ViewModel() {

    val flow = MutableLiveData<List<FlowEntity>>()

    fun getData() = launch(appExecutors.uiContext) {
        val result = cashRepository.getAllFlow()
        if (result is Result.Success) {
            Collections.sort(result.data) { o1, o2 -> o1.date.compareTo(o2.date) }
            flow.value = result.data
            Log.d("FlowViewModel", "success")
        } else {
            Log.d("FlowViewModel", "error")
        }
    }
}