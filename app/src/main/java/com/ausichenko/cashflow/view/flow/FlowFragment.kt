package com.ausichenko.cashflow.view.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ausichenko.cashflow.R
import com.ausichenko.cashflow.view.NavigationFragment
import kotlinx.android.synthetic.main.fragment_categories.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlowFragment : NavigationFragment() {

    companion object{
        fun newInstance(): FlowFragment{
            return FlowFragment()
        }
    }

    private val addFlowFragment = AddFlowFragment()

    val flowViewModel: FlowViewModel by viewModel()
    private val flowAdapter = FlowAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_flow, container, false)

        initFlowsView(view)
        initAddFlowDialog()

        return view
    }

    private fun initFlowsView(view: View) {
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        view.recyclerView.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
        view.recyclerView.adapter = flowAdapter
    }

    private fun initAddFlowDialog() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flowViewModel.flow.observe(this, Observer {
            flowAdapter.flows = it
            flowAdapter.notifyDataSetChanged()
        })

        flowViewModel.getData()
    }

    override fun onFabClick() {
        addFlowFragment.categoriesList = flowViewModel.categories.value!!
        addFlowFragment.show(childFragmentManager, addFlowFragment.tag)
    }
}