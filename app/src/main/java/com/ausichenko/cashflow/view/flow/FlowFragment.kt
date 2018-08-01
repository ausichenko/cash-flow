package com.ausichenko.cashflow.view.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ausichenko.cashflow.R
import com.ausichenko.cashflow.view.NavigationFragment

class FlowFragment : NavigationFragment() {

    companion object{
        fun newInstance(): FlowFragment{
            return FlowFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_flow, container, false)
    }

    override fun onFabClick() {
        Toast.makeText(context, "Add Flow", Toast.LENGTH_LONG).show()
    }
}