package com.ausichenko.cashflow.view.flow

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ausichenko.cashflow.R
import com.ausichenko.cashflow.data.database.entites.CategoryEntity
import com.ausichenko.cashflow.view.categories.AddCategoryFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_category.view.*
import kotlinx.android.synthetic.main.fragment_flow_add.view.*
import android.widget.ArrayAdapter



class AddFlowFragment : BottomSheetDialogFragment() {

    lateinit var categoriesList: List<CategoryEntity>
    lateinit var saveCategoryListener: OnSaveFlowListener

    init {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_BottomSheet)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_flow_add, container, false)


        val list = ArrayList<String>()
        list.add("Not selected")
        categoriesList.forEach { list.add(it.name) }

        val dataAdapter = ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, list)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        view.categoriesSpinner.adapter = dataAdapter

        /*
        view.save_button.setOnClickListener {
            val name = view.name_edit_text.text.toString()
            if (TextUtils.isEmpty(name)) {
                return@setOnClickListener
            }

            //saveCategoryListener.onSaveCategory(name)

            //view.name_edit_text.setText("")
            //dismiss()
        }
        */

        return view
    }

    interface OnSaveFlowListener {
        fun onSaveCategory(name: String)
    }
}