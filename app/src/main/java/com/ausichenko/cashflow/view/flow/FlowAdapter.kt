package com.ausichenko.cashflow.view.flow

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ausichenko.cashflow.R
import com.ausichenko.cashflow.data.database.entites.FlowEntity
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
import kotlinx.android.synthetic.main.item_flow.view.*
import kotlinx.android.synthetic.main.item_flow_header.view.*
import java.text.SimpleDateFormat
import java.util.*

class FlowAdapter : RecyclerView.Adapter<FlowAdapter.ViewHolder>(), StickyRecyclerHeadersAdapter<FlowAdapter.HeaderViewHolder> {

    private val itemViewType = 1
    private val footerViewType = 2

    var flows: MutableList<FlowEntity> = mutableListOf()

    fun removeItem(position: Int) {
        flows.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, flows.size)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == flows.size) footerViewType else itemViewType
    }

    override fun getHeaderId(position: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.time = flows[position].date

        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.time.time
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup?): HeaderViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_flow_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: HeaderViewHolder, position: Int) {
        val flow = flows[position]
        holder.bind(flow.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
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
            val flow = flows[position]
            holder.bind(flow)
        }
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class HeaderViewHolder(itemView: View) : ViewHolder(itemView) {

        @SuppressLint("SimpleDateFormat")
        fun bind(date: Date) {
            val format = SimpleDateFormat("d MMM yyyy")
            itemView.title.text = format.format(date.time)
        }
    }

    class ItemViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bind(flowEntity: FlowEntity) {
            itemView.name.text = flowEntity.name
            itemView.description.text = flowEntity.description

            itemView.price.text = flowEntity.price.toString()
        }
    }

    class FooterViewHolder(itemView: View) : ViewHolder(itemView)
}