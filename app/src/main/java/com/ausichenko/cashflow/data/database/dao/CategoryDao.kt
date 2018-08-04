package com.ausichenko.cashflow.data.database.dao

import androidx.room.*
import com.ausichenko.cashflow.data.database.entites.CategoryEntity

@Dao
interface CategoryDao {

    @Query("select * from categories")
    fun getAll(): List<CategoryEntity>

    @Query("select * from categories where id == :id limit 1")
    fun get(id: Long): CategoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryEntity: CategoryEntity)

    @Update
    fun update(categoryEntity: CategoryEntity)

    @Delete
    fun delete(categoryEntity: CategoryEntity)
}