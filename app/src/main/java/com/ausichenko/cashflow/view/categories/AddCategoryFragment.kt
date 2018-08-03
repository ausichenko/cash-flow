package com.ausichenko.cashflow.view.categories

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ausichenko.cashflow.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_category.view.*

class AddCategoryFragment : BottomSheetDialogFragment() {

    lateinit var saveCategoryListener: OnSaveCategoryListener

    init {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_BottomSheet)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_category, container, false)

        view.save_button.setOnClickListener {
            val name = view.name_edit_text.text.toString()
            if (TextUtils.isEmpty(name)) {
                return@setOnClickListener
            }

            saveCategoryListener.onSaveCategory(name)

            view.name_edit_text.setText("")
            dismiss()
        }

        return view
    }

    interface OnSaveCategoryListener {
        fun onSaveCategory(name: String)
    }
}