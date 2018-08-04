package com.ausichenko.cashflow.view.flow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ausichenko.cashflow.R
import com.ausichenko.cashflow.data.database.entites.FlowEntity
import kotlinx.android.synthetic.main.item_flow.view.*

class FlowAdapter : RecyclerView.Adapter<FlowAdapter.ViewHolder>() {

    private val headerViewType = 1
    private val itemViewType = 2
    private val footerViewType = 3

    var flows: List<FlowEntity> = ArrayList()

    override fun getItemViewType(position: Int): Int {
        return if (position == flows.size) footerViewType else itemViewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            headerViewType -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_header, parent, false)
                HeaderViewHolder(view)
            }
            itemViewType -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flow, parent, false)
                ItemViewHolder(view)
            }
            footerViewType -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_footer, parent, false)
                FooterViewHolder(view)
            }
            else -> throw IllegalArgumentException("no such viewType")
        }
    }

    override fun getItemCount(): Int {
        return flows.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val category = flows[position]
            holder.bind(category)
        }
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class HeaderViewHolder(itemView: View) : ViewHolder(itemView)

    class ItemViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bind(flowEntity: FlowEntity) {
            itemView.name.text = flowEntity.name
            itemView.description.text = flowEntity.description
            itemView.price.text = flowEntity.price.toString()
        }
    }

    class FooterViewHolder(itemView: View) : ViewHolder(itemView)
}