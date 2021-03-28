package com.eliseylobanov.acrosstheuniverse.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProviders.of(this).get(WeatherViewModel::class.java)
    }

    lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }
}