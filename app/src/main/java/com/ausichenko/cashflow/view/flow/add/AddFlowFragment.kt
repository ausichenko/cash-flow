package com.ausichenko.cashflow.view.flow.add

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
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
import java.text.SimpleDateFormat
import java.util.*

class AddFlowFragment : BottomSheetDialogFragment() {

    lateinit var saveListener: OnSaveFlowListener

    val viewModel: AddFlowViewModel by viewModel()

    private lateinit var categoriesSpinner: Spinner

    private val date = Calendar.getInstance()

    init {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_BottomSheet)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_flow_add, container, false)

        categoriesSpinner = view.categoriesSpinner

        initDatePicker(view)
        initSave(view)

        return view
    }

    @SuppressLint("SimpleDateFormat")
    private fun initDatePicker(view: View) {
        val format = SimpleDateFormat("d MMM yyyy")
        view.dateButton.text = format.format(date.time)
        view.dateButton.setOnClickListener {
            DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                date.set(Calendar.YEAR, year)
                date.set(Calendar.MONTH, month)
                date.set(Calendar.DAY_OF_MONTH, day)
                view.dateButton.text = format.format(date.time)
            }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun initSave(view: View) {
        view.saveButton.setOnClickListener {
            val name = view.nameEditText.text.toString()
            val description = view.descriptionEditText.text.toString()
            val price = view.priceEditText.text.toString()
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(price)) {
                return@setOnClickListener
            }
            val categoryId = (categoriesSpinner.selectedItem as CategoryEntity).id
            viewModel.saveFlow(categoryId!!, date.time, name, description, price.toDouble())

            view.nameEditText.setText("")
            view.descriptionEditText.setText("")
            view.priceEditText.setText("")

            saveListener.onSave()
            dismiss()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.categories.observe(this, Observer {
            val categoriesDropDownAdapter = CategoriesDropDownAdapter(context!!,
                    android.R.layout.simple_spinner_item, 0, it)
            categoriesSpinner.adapter = categoriesDropDownAdapter
        })

        viewModel.getCategories()
    }

    interface OnSaveFlowListener {
        fun onSave()
    }
}