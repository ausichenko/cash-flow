package com.ausichenko.cashflow.data.repository

import com.ausichenko.cashflow.data.database.dao.CategoryDao
import com.ausichenko.cashflow.data.database.entites.CategoryEntity
import com.ausichenko.cashflow.data.exeptions.LocalDataNotFoundException
import com.ausichenko.cashflow.data.models.Result
import com.ausichenko.cashflow.utils.AppExecutors
import kotlinx.coroutines.experimental.withContext

class CashRepositoryImpl(
        private val appExecutors: AppExecutors,
        private val categoryDao: CategoryDao) : CashRepository {

    override fun giveHello() = "Hello Koin"

    override suspend fun getAllCategories(): Result<List<CategoryEntity>> = withContext(appExecutors.ioContext) {
        val categories = categoryDao.getAll()
        if (categories.isNotEmpty()) {
            Result.Success(categories)
        } else {
            Result.Error(LocalDataNotFoundException())
        }
    }

    override suspend fun saveCategory(categoryEntity: CategoryEntity) = withContext(appExecutors.ioContext) {
        categoryDao.insert(categoryEntity)
    }

    override suspend fun updateCategory(categoryEntity: CategoryEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteCategory(categoryEntity: CategoryEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}