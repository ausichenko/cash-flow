package com.ausichenko.cashflow.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ausichenko.cashflow.data.database.dao.CategoryDao
import com.ausichenko.cashflow.data.database.dao.FlowDao
import com.ausichenko.cashflow.data.database.entites.CategoryEntity
import com.ausichenko.cashflow.data.database.entites.FlowEntity

@Database(entities = [(CategoryEntity::class), (FlowEntity::class)], version = 1)
abstract class CashDatabase : RoomDatabase() {

    companion object {
        const val databaseName = "cash_database"
    }

    abstract fun categoryDao(): CategoryDao
    abstract fun flowDao(): FlowDao
}