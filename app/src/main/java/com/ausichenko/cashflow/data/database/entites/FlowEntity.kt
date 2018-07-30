package com.ausichenko.cashflow.data.database.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flow")
data class FlowEntity(
        @PrimaryKey val int: Int,
        val date: Int,
        @ColumnInfo(name = "category_id")
        val categoryId: Int,
        val name: String,
        val description: String,
        val price: Double
)