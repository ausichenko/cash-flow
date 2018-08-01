package com.ausichenko.cashflow.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ausichenko.cashflow.R
import com.ausichenko.cashflow.utils.getCurrentFragment
import com.ausichenko.cashflow.utils.replaceFragmentInActivity
import com.ausichenko.cashflow.view.categories.CategoryFragment
import com.ausichenko.cashflow.view.flow.FlowFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val navigationDrawer = BottomNavigationDrawer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        initBottomAppBar()
        navigationDrawer.setOnItemClickListener(this)
        replaceFragmentInActivity(CategoryFragment.newInstance(), container.id)
    }

    private fun initBottomAppBar() {
        val bar = findViewById<BottomAppBar>(R.id.bar)
        bar.setNavigationIcon(R.drawable.ic_menu)
        bar.setNavigationOnClickListener {
            navigationDrawer.show(supportFragmentManager, navigationDrawer.tag)
        }

        fab.setOnClickListener {
            val currentFragment = getCurrentFragment()
            (currentFragment as? NavigationFragment)?.onFabClick()
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.navigation_categories -> {
                replaceFragmentInActivity(CategoryFragment.newInstance(), container.id)
                true
            }
            R.id.navigation_flow -> {
                replaceFragmentInActivity(FlowFragment.newInstance(), container.id)
                true
            }
            else -> false
        }
    }
}
