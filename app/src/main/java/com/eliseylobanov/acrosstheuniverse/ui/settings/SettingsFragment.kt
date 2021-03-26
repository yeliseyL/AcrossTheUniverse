package com.eliseylobanov.acrosstheuniverse.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.radioGroup.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.earthRadioButton -> changeThemeTo(R.style.Theme_AcrossTheUniverse,
                        R.color.colorPrimaryDark)
                R.id.marsRadioButton -> changeThemeTo(R.style.Theme_AcrossTheUniverse_Mars,
                        R.color.colorPrimaryDarkMars)
                R.id.moonRadioButton -> changeThemeTo(R.style.Theme_AcrossTheUniverse_Moon,
                        R.color.colorPrimaryDarkMoon)
            }
            view.findNavController().navigate(R.id.action_settingsFragment_to_photoOfDayFragment)
        }
    }

    private fun changeThemeTo(themeId: Int, colorId: Int) {
        requireActivity().setTheme(themeId)
        requireActivity().window.statusBarColor = resources.getColor(colorId)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putInt(getString(R.string.saved_theme), themeId)
            putInt(getString(R.string.saved_color), colorId)
            apply()
        }
    }
}