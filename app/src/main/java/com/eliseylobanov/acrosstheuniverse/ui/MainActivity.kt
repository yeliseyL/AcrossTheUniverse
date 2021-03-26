package com.eliseylobanov.acrosstheuniverse.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eliseylobanov.acrosstheuniverse.R

const val DEFAULT_THEME = R.style.Theme_AcrossTheUniverse
const val DEFAULT_STATUSBARCOLOR = R.color.colorPrimaryDark

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNewTheme()
        setContentView(R.layout.activity_main)
    }

    private fun setNewTheme() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        val newTheme = sharedPref.getInt(getString(R.string.saved_theme), DEFAULT_THEME)
        val newStatusBarColor = sharedPref.getInt(getString(R.string.saved_color), DEFAULT_STATUSBARCOLOR)
        window.statusBarColor = newStatusBarColor
        setTheme(newTheme)
    }
}