package com.eliseylobanov.acrosstheuniverse.ui

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.eliseylobanov.acrosstheuniverse.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

const val DEFAULT_THEME = R.style.Theme_AcrossTheUniverse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNewTheme()
        setContentView(R.layout.activity_main)
        setUpNavigation()
        setSupportActionBar(toolbar)
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

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            title = when (destination.id) {
                R.id.earthFragment -> "Across The Universe - Earth"
                R.id.marsFragment -> "Across The Universe - Mars"
                R.id.settingsFragment -> "Across The Universe - Settings"
                R.id.weatherFragment -> "Across The Universe - Weather"
                R.id.pictureOfDayFragment -> "Across The Universe - Pictures"
                else -> "Across The Universe"
            }
        }
    }
}