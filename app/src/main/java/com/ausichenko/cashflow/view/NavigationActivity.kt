package com.ausichenko.cashflow.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.ausichenko.cashflow.R
import com.google.android.material.bottomappbar.BottomAppBar
import org.koin.android.architecture.ext.viewModel

class NavigationActivity : AppCompatActivity() {

    val navigationViewModel : NavigationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val bar = findViewById<BottomAppBar>(R.id.bar)
        // todo: use androidx and replace di to dagger
        setSupportActionBar(bar)

        Log.d("TEST", navigationViewModel.sayHello())
    }
}
