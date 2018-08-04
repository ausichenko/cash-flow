package com.ausichenko.cashflow.view.flow.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.ausichenko.cashflow.R
import com.ausichenko.cashflow.data.database.entites.CategoryEntity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_flow_add.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddFlowFragment : BottomSheetDialogFragment() {

    val viewModel: AddFlowViewModel by viewModel()

    private lateinit var categoriesSpinner: Spinner

    init {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_BottomSheet)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_flow_add, container, false)

        categoriesSpinner = view.categoriesSpinner
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.categories.observe(this, Observer {
            initCategoriesSpinner(it)
        })

        viewModel.getCategories()
    }

    private fun initCategoriesSpinner(categories: List<CategoryEntity>) {
        val categoriesDropDownAdapter = CategoriesDropDownAdapter(context!!,
                android.R.layout.simple_spinner_item, 0, categories)
        categoriesSpinner.adapter = categoriesDropDownAdapter
    }
}