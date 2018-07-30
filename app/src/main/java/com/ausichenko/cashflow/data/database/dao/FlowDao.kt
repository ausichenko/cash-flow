package com.ausichenko.cashflow.data.database.dao

import androidx.room.*
import com.ausichenko.cashflow.data.database.entites.FlowEntity

@Dao
interface FlowDao {

    @Query("select * from flow")
    fun getAll(): List<FlowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(flowEntity: FlowEntity)

    @Delete
    fun delete(flowEntity: FlowEntity)
}