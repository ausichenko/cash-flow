package com.ausichenko.cashflow.view.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ausichenko.cashflow.R
import com.ausichenko.cashflow.data.database.entites.CategoryEntity
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val headerViewType = 1
    private val itemViewType = 2

    var categories: List<CategoryEntity> = ArrayList()

    override fun getItemViewType(position: Int): Int {
        return itemViewType// if (position == 0) headerViewType else itemViewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            headerViewType -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_header, parent, false)
                return HeaderViewHolder(view)
            }
            itemViewType -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
                return ItemViewHolder(view)
            }
            else -> throw IllegalArgumentException("no such viewType")
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val category = categories[position]
            holder.bind(category)
        }
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class HeaderViewHolder(itemView: View) : ViewHolder(itemView)

    class ItemViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bind(categoryEntity: CategoryEntity) {
            itemView.name.text = categoryEntity.name
        }
    }
}