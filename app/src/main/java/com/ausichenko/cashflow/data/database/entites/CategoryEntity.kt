package com.ausichenko.cashflow.data.database.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long?,
        var name: String
) {
        constructor():this(null, "")
}