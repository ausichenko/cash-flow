package com.ausichenko.cashflow.data.repository

import com.ausichenko.cashflow.data.database.entites.CategoryEntity
import com.ausichenko.cashflow.data.models.Result

interface CashRepository {
    fun giveHello(): String

    suspend fun getAllCategories(): Result<List<CategoryEntity>>
    suspend fun saveCategory(categoryEntity: CategoryEntity)
    suspend fun updateCategory(categoryEntity: CategoryEntity)
    suspend fun deleteCategory(categoryEntity: CategoryEntity)


}