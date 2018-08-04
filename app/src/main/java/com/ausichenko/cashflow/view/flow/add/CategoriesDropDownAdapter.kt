package com.ausichenko.cashflow.view.flow.add

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.ausichenko.cashflow.data.database.entites.CategoryEntity

class CategoriesDropDownAdapter(context: Context,
                                @LayoutRes private val layoutResource: Int,
                                @IdRes private val textViewResourceId: Int = 0,
                                private val values: List<CategoryEntity>) : ArrayAdapter<CategoryEntity>(context, layoutResource, values) {

    override fun getItem(position: Int): CategoryEntity = values[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = createViewFromResource(convertView, parent, layoutResource)

        return bindData(getItem(position), view)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = createViewFromResource(convertView, parent, android.R.layout.simple_spinner_dropdown_item)

        return bindData(getItem(position), view)
    }

    private fun createViewFromResource(convertView: View?, parent: ViewGroup, layoutResource: Int): TextView {
        val context = parent.context
        val view = convertView ?: LayoutInflater.from(context).inflate(layoutResource, parent, false)
        return try {
            if (textViewResourceId == 0) view as TextView
            else {
                view.findViewById(textViewResourceId) ?:
                throw RuntimeException("Failed to find view with ID " +
                        "${context.resources.getResourceName(textViewResourceId)} in item layout")
            }
        } catch (ex: ClassCastException){
            throw IllegalStateException(
                    "ArrayAdapter requires the resource ID to be a TextView", ex)
        }
    }

    private fun bindData(value: CategoryEntity, view: TextView): TextView {
        view.text = value.name
        return view
    }
}
