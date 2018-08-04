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
import com.ausichenko.cashflow.view.flow.add.AddFlowFragment
import kotlinx.android.synthetic.main.fragment_categories.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlowFragment : NavigationFragment() {

    companion object{
        fun newInstance(): FlowFragment{
            return FlowFragment()
        }
    }

    private val addFlowFragment = AddFlowFragment()

    val viewModel: FlowViewModel by viewModel()
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
        addFlowFragment.saveListener = object : AddFlowFragment.OnSaveFlowListener {
            override fun onSave() {
                viewModel.getData()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.flow.observe(this, Observer {
            flowAdapter.flows = it
            flowAdapter.notifyDataSetChanged()
        })

        viewModel.getData()
    }

    override fun onFabClick() {
        addFlowFragment.show(childFragmentManager, addFlowFragment.tag)
    }
}