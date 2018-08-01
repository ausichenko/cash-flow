package com.ausichenko.cashflow.data.database.dao

import androidx.room.*
import com.ausichenko.cashflow.data.database.entites.FlowEntity

@Dao
interface FlowDao {

    @Query("select * from flow")
    fun getAll(): List<FlowEntity>

    @Query("select * from flow where category_id == :categoryId")
    fun getByCategaryId(categoryId: Long): List<FlowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(flowEntity: FlowEntity)

    @Update
    fun update(flowEntity: FlowEntity)

    @Delete
    fun delete(flowEntity: FlowEntity)
}