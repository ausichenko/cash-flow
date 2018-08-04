package com.ausichenko.cashflow.data.database.entites

import androidx.room.*
import java.util.*

@Entity(tableName = "flow",
        foreignKeys = [(ForeignKey(
                entity = CategoryEntity::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("category_id"),
                onDelete = ForeignKey.CASCADE))])
data class FlowEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long?,
        var date: Date,
        @ColumnInfo(name = "category_id")
        var categoryId: Long?,
        var name: String,
        var description: String,
        var price: Double
) {
        constructor():this(null, Date(), null, "", "", 0.0)
}