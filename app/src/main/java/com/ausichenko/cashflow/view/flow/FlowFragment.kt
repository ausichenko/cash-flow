package com.ausichenko.cashflow.view.flow

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ausichenko.cashflow.R
import com.ausichenko.cashflow.view.NavigationFragment
import com.ausichenko.cashflow.view.flow.add.AddFlowFragment
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration
import kotlinx.android.synthetic.main.fragment_flow.view.*
import kotlinx.android.synthetic.main.item_flow.view.*
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
        val headersDecor = StickyRecyclerHeadersDecoration(flowAdapter)

        view.recyclerView.layoutManager = LinearLayoutManager(context)
        view.recyclerView.addItemDecoration(headersDecor)
        view.recyclerView.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
        view.recyclerView.adapter = flowAdapter

        initItemTouchHelper().attachToRecyclerView(view.recyclerView)
    }

    private fun initItemTouchHelper(): ItemTouchHelper {
        return ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                val deletedModel = flowAdapter.flows.get(position)
                flowAdapter.removeItem(position)
                viewModel.deleteFlow(deletedModel)
            }

            override fun onChildDrawOver(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                val foregroundView = (viewHolder as FlowAdapter.ItemViewHolder).itemView.viewForeground
                getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                        actionState, isCurrentlyActive)
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                val foregroundView = (viewHolder as FlowAdapter.ItemViewHolder).itemView.viewForeground

                getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                        actionState, isCurrentlyActive)
            }
        })
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
            flowAdapter.flows = it.toMutableList()
            flowAdapter.notifyDataSetChanged()
        })

        viewModel.getData()
    }

    override fun onFabClick() {
        addFlowFragment.show(childFragmentManager, addFlowFragment.tag)
    }
}