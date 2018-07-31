package com.ausichenko.cashflow.view

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.ausichenko.cashflow.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.bottom_navigation.view.*


class BottomNavigationDrawer : BottomSheetDialogFragment() {

    lateinit var navigationItemSelectedListener: NavigationView.OnNavigationItemSelectedListener

    init {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_BottomSheet)
    }

    fun setOnItemClickListener(selectedListener: NavigationView.OnNavigationItemSelectedListener) {
        navigationItemSelectedListener = selectedListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottom_navigation, container, false)


        view.navigation_view.setNavigationItemSelectedListener {
            view.navigation_view.setCheckedItem(it)
            dismiss()
            navigationItemSelectedListener.onNavigationItemSelected(it)
        }

        return view
    }
}