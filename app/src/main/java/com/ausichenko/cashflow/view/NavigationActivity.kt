package com.ausichenko.cashflow.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ausichenko.cashflow.R
import com.google.android.material.bottomappbar.BottomAppBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavigationActivity : AppCompatActivity() {

    val navigationViewModel : NavigationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val bar = findViewById<BottomAppBar>(R.id.bar)
        setSupportActionBar(bar)

        Log.d("TEST", navigationViewModel.sayHello())
    }
}
