package com.ausichenko.cashflow.data.repository

import com.ausichenko.cashflow.data.database.entites.CategoryEntity
import com.ausichenko.cashflow.data.database.entites.FlowEntity
import com.ausichenko.cashflow.data.models.Result

interface CashRepository {
    fun giveHello(): String

    suspend fun getAllCategories(): Result<List<CategoryEntity>>
    suspend fun saveCategory(categoryEntity: CategoryEntity)
    suspend fun updateCategory(categoryEntity: CategoryEntity)
    suspend fun deleteCategory(categoryEntity: CategoryEntity)

    suspend fun getAllFlow(): Result<List<FlowEntity>>
    suspend fun getByCategoryId(categoryId: Long): Result<List<FlowEntity>>
    suspend fun saveFlow(flowEntity: FlowEntity)
    suspend fun updateFlow(flowEntity: FlowEntity)
    suspend fun deleteFlow(flowEntity: FlowEntity)
}