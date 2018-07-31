package com.ausichenko.cashflow.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ausichenko.cashflow.R
import com.google.android.material.bottomappbar.BottomAppBar
import kotlinx.android.synthetic.main.activity_navigation.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavigationActivity : AppCompatActivity() {

    val navigationViewModel : NavigationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val bar = findViewById<BottomAppBar>(R.id.bar)
        setSupportActionBar(bar)

        navigationViewModel.categories.observe(this, Observer {
            for (categoryEntity in it) {
                Log.d("CATT", categoryEntity.name)
            }
            Log.d("CATT", "end")
        })
        navigationViewModel.getCategories()

        fab.setOnClickListener {
            navigationViewModel.addNewCat()
        }
    }
}
