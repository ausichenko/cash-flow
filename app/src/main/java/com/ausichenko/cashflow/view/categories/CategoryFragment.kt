package com.ausichenko.cashflow.view.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ausichenko.cashflow.R
import com.ausichenko.cashflow.view.NavigationFragment
import kotlinx.android.synthetic.main.fragment_categories.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : NavigationFragment() {

    companion object{
        fun newInstance(): CategoryFragment{
            return CategoryFragment()
        }
    }

    val categoryViewModel: CategoryViewModel by viewModel()
    private val categoryAdapter = CategoryAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        initCategoriesView(view)

        return view
    }

    private fun initCategoriesView(view: View) {
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        view.recyclerView.adapter = categoryAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryViewModel.categories.observe(this, Observer {
            categoryAdapter.categories = it
            categoryAdapter.notifyDataSetChanged()
        })

        categoryViewModel.getCategories()
    }

    override fun onFabClick() {
        Toast.makeText(context, "Add Category", Toast.LENGTH_LONG).show()
        //navigationViewModel.addNewCat()
    }
}