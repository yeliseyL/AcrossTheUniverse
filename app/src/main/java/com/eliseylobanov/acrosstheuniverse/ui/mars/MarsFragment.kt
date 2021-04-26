package com.eliseylobanov.acrosstheuniverse.ui.mars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.eliseylobanov.acrosstheuniverse.ApiStatus
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.databinding.FragmentMarsBinding

class MarsFragment : Fragment(R.layout.fragment_mars) {

    private val viewModel: MarsViewModel by lazy {
        ViewModelProviders.of(this).get(MarsViewModel::class.java)
    }

    lateinit var binding: FragmentMarsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMarsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val viewPagerAdapter = MarsViewPagerAdapter()
        binding.marsViewPager.adapter = viewPagerAdapter

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