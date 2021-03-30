package com.eliseylobanov.acrosstheuniverse.ui.earth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.eliseylobanov.acrosstheuniverse.ApiStatus
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.databinding.FragmentEarthBinding

class EarthFragment : Fragment(R.layout.fragment_earth) {

    private val viewModel: EarthViewModel by lazy {
        ViewModelProviders.of(this).get(EarthViewModel::class.java)
    }

    lateinit var binding: FragmentEarthBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentEarthBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val viewPagerAdapter = EarthViewPagerAdapter()
        binding.earthViewPager.adapter = viewPagerAdapter

        viewModel.status.observe(viewLifecycleOwner, {
            when (it) {
                ApiStatus.LOADING -> binding.progress.visibility = View.VISIBLE
                ApiStatus.DONE -> binding.progress.visibility = View.GONE
                ApiStatus.ERROR -> View.GONE
            }
        })

        return binding.root
    }
}