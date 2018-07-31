package com.ausichenko.cashflow.view.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ausichenko.cashflow.R

class CategoryFragment : Fragment() {

    companion object{
        fun newInstance(): CategoryFragment{
            return CategoryFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    /*
        bottomMenu.navigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_categories -> {
                    replaceFragmentInActivity(CategoryFragment.newInstance(), container.id)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_flow -> {
                    replaceFragmentInActivity(FlowFragment.newInstance(), container.id)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
        */
    /*
    navigationViewModel.categories.observe(this, Observer {
        for (categoryEntity in it) {
            Log.d("CATT", categoryEntity.name)
        }
        Log.d("CATT", "end")
    })
    navigationViewModel.getCategories()

    //fab
    //navigationViewModel.addNewCat()
    */
}