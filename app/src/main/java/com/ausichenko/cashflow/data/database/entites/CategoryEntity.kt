package com.ausichenko.cashflow.data.database.entites

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "categories",
        foreignKeys = [(ForeignKey(entity = FlowEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("category_id"), onDelete = CASCADE))])
data class CategoryEntity(
        @PrimaryKey val id: Int,
        val name: String
)