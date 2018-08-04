package com.ausichenko.cashflow.view.flow.add

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.ausichenko.cashflow.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_category.view.*
import kotlinx.android.synthetic.main.fragment_flow_add.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class AddFlowFragment : BottomSheetDialogFragment() {

    val viewModel: AddFlowViewModel by viewModel()

    private lateinit var categoriesSpinner: Spinner
    private lateinit var nameEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var saveButton: Button

    private val date = Calendar.getInstance()

    init {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_BottomSheet)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_flow_add, container, false)

        categoriesSpinner = view.categoriesSpinner
        nameEditText = view.nameEditText
        descriptionEditText = view.descriptionEditText
        priceEditText = view.priceEditText
        saveButton = view.saveButton

        initDateButton(view)
        view.saveButton.setOnClickListener {
            val name = view.name_edit_text.text.toString()
            if (TextUtils.isEmpty(name)) {
                return@setOnClickListener
            }

            //saveCategoryListener.onSaveCategory(name)

            //view.name_edit_text.setText("")
            //dismiss()
        }
        //*/

        return view
    }

    private fun initDateButton(view: View) {
        val format = SimpleDateFormat("d MMM yyyy")
        view.dateButton.text = format.format(date.time)
        view.dateButton.setOnClickListener {
            DatePickerDialog(context, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                date.set(Calendar.YEAR, year)
                date.set(Calendar.MONTH, month)
                date.set(Calendar.DAY_OF_MONTH, day)
                view.dateButton.text = format.format(date.time)
            }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)).show()
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
}