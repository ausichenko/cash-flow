package com.ausichenko.cashflow.data.repository

import com.ausichenko.cashflow.data.database.dao.CategoryDao
import com.ausichenko.cashflow.data.database.dao.FlowDao
import com.ausichenko.cashflow.data.database.entites.CategoryEntity
import com.ausichenko.cashflow.data.database.entites.FlowEntity
import com.ausichenko.cashflow.data.exeptions.LocalDataNotFoundException
import com.ausichenko.cashflow.data.models.Result
import com.ausichenko.cashflow.utils.AppExecutors
import kotlinx.coroutines.experimental.withContext

class CashRepositoryImpl(
        private val appExecutors: AppExecutors,
        private val categoryDao: CategoryDao,
        private val flowDao: FlowDao) : CashRepository {

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

    override suspend fun updateCategory(categoryEntity: CategoryEntity) = withContext(appExecutors.ioContext) {
        categoryDao.update(categoryEntity)
    }

    override suspend fun deleteCategory(categoryEntity: CategoryEntity) = withContext(appExecutors.ioContext) {
        categoryDao.delete(categoryEntity)
    }

    override suspend fun getAllFlow(): Result<List<FlowEntity>> = withContext(appExecutors.ioContext) {
        val flow = flowDao.getAll()
        if (flow.isNotEmpty()) {
            Result.Success(flow)
        } else {
            Result.Error(LocalDataNotFoundException())
        }
    }

    override suspend fun getByCategoryId(categoryId: Long): Result<List<FlowEntity>> = withContext(appExecutors.ioContext) {
        val flow = flowDao.getByCategaryId(categoryId)
        if (flow.isNotEmpty()) {
            Result.Success(flow)
        } else {
            Result.Error(LocalDataNotFoundException())
        }
    }

    override suspend fun saveFlow(flowEntity: FlowEntity) = withContext(appExecutors.ioContext) {
        flowDao.insert(flowEntity)
    }

    override suspend fun updateFlow(flowEntity: FlowEntity) = withContext(appExecutors.ioContext) {
        flowDao.update(flowEntity)
    }

    override suspend fun deleteFlow(flowEntity: FlowEntity) = withContext(appExecutors.ioContext) {
        flowDao.delete(flowEntity)
    }
}