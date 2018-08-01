package com.ausichenko.cashflow.view.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ausichenko.cashflow.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_category.view.*

class AddCategoryFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_category, container, false)

        view.save_button.setOnClickListener {
            val name = view.name_edit_text.text
            Toast.makeText(context, "Saved: $name", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}