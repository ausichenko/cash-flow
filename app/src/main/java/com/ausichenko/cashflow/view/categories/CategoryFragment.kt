package com.ausichenko.cashflow.view.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ausichenko.cashflow.R
import com.ausichenko.cashflow.view.NavigationFragment
import com.ausichenko.cashflow.view.categories.add.AddCategoryFragment
import com.ausichenko.cashflow.view.categories.add.AddCategoryFragment.OnSaveCategoryListener
import kotlinx.android.synthetic.main.fragment_categories.view.*
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
    }

    private fun initAddCategoryFragment() {
        addCategoryFragment.saveCategoryListener = object : OnSaveCategoryListener {
            override fun onSaveCategory(name: String) {
                viewModel.saveCategory(name)
                viewModel.getCategories()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.categories.observe(this, Observer {
            categoryAdapter.categories = it
            categoryAdapter.notifyDataSetChanged()
        })

        viewModel.getCategories()
    }

    override fun onFabClick() {
        addCategoryFragment.show(childFragmentManager, addCategoryFragment.tag)
    }
}