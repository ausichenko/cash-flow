package com.ausichenko.cashflow.view.categories

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ausichenko.cashflow.R
import com.ausichenko.cashflow.view.NavigationFragment
import com.ausichenko.cashflow.view.categories.add.AddCategoryFragment
import com.ausichenko.cashflow.view.categories.add.AddCategoryFragment.OnSaveCategoryListener
import kotlinx.android.synthetic.main.fragment_categories.view.*
import kotlinx.android.synthetic.main.item_category.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : NavigationFragment() {

    companion object{
        fun newInstance(): CategoryFragment{
            return CategoryFragment()
        }
    }

    val viewModel: CategoryViewModel by viewModel()
    private val categoryAdapter = CategoryAdapter()

    private val addCategoryFragment = AddCategoryFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        initCategoriesView(view)
        initAddCategoryFragment()

        return view
    }

    private fun initCategoriesView(view: View) {
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        view.recyclerView.adapter = categoryAdapter

        initItemTouchHelper().attachToRecyclerView(view.recyclerView)
    }

    private fun initItemTouchHelper(): ItemTouchHelper {
        return ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                val deletedModel = categoryAdapter.categories.get(position)
                categoryAdapter.removeItem(position)
                viewModel.deleteCategory(deletedModel)
            }

            override fun onChildDrawOver(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                val foregroundView = (viewHolder as CategoryAdapter.ItemViewHolder).itemView.viewForeground
                getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                        actionState, isCurrentlyActive)
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                val foregroundView = (viewHolder as CategoryAdapter.ItemViewHolder).itemView.viewForeground

                getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                        actionState, isCurrentlyActive)
            }
        })
    }

    private fun initAddCategoryFragment() {
        addCategoryFragment.saveListener = object : OnSaveCategoryListener {
            override fun onSave() {
                viewModel.getCategories()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.categories.observe(this, Observer {
            categoryAdapter.categories = it.toMutableList()
            categoryAdapter.notifyDataSetChanged()
        })

        viewModel.getCategories()
    }

    override fun onFabClick() {
        addCategoryFragment.show(childFragmentManager, addCategoryFragment.tag)
    }
}