package com.eliseylobanov.acrosstheuniverse.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.eliseylobanov.acrosstheuniverse.R
import com.google.android.material.bottomnavigation.BottomNavigationView

const val DEFAULT_THEME = R.style.Theme_AcrossTheUniverse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNewTheme()
        setContentView(R.layout.activity_main)
        setUpNavigation()
    }

    private fun setNewTheme() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        val newTheme = sharedPref.getInt(getString(R.string.saved_theme), DEFAULT_THEME)
        setTheme(newTheme)
    }

    private fun setUpNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)
    }
}