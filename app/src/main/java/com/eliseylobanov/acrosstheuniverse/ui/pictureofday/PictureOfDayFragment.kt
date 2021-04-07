package com.eliseylobanov.acrosstheuniverse.ui.pictureofday

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.eliseylobanov.acrosstheuniverse.ApiStatus
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.databinding.FragmentPictureOfDayBinding
import com.eliseylobanov.acrosstheuniverse.getDayBeforeYesterday
import com.eliseylobanov.acrosstheuniverse.getYesterday
import com.google.android.material.chip.Chip
import kotlin.math.roundToInt


class PictureOfDayFragment : Fragment(R.layout.fragment_picture_of_day) {

    private val viewModel: PictureOfDayViewModel by lazy {
        ViewModelProviders.of(this).get(PictureOfDayViewModel::class.java)
    }

    lateinit var binding: FragmentPictureOfDayBinding
    private var isExpanded = false


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPictureOfDayBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.status.observe(viewLifecycleOwner, {
            when (it) {
                ApiStatus.LOADING -> binding.progress.visibility = View.VISIBLE
                ApiStatus.DONE -> binding.progress.visibility = View.GONE
                ApiStatus.ERROR -> View.GONE
            }
        })

        binding.image.setOnClickListener {
            isExpanded = !isExpanded
            if (container != null) {
                TransitionManager.beginDelayedTransition(
                    container, TransitionSet()
                        .addTransition(ChangeBounds())
                        .addTransition(ChangeImageTransform())
                )
            }
            val params: ViewGroup.LayoutParams = binding.image.layoutParams
            params.height =
                if (isExpanded) resources.getDimension(R.dimen.imageView_height_scaled).roundToInt()
                else resources.getDimension(R.dimen.imageView_height).roundToInt()
            binding.image.layoutParams = params
            binding.image.scaleType =
                if (isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
        }

            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }

        binding.chipGroup.setOnCheckedChangeListener { chipGroup, position ->
            chipGroup.findViewById<Chip>(position)?.let {
                when (it) {
                    binding.dayBeforeYesterday -> viewModel.getPictureOfDay(getDayBeforeYesterday())
                    binding.yesterday -> viewModel.getPictureOfDay(getYesterday())
                    binding.today -> viewModel.getTodayPictureOfDay()
                }
            }
        }
    }
}