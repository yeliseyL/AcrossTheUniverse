package com.eliseylobanov.acrosstheuniverse.ui.pictureofday

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.databinding.FragmentPictureOfDayBinding
import com.eliseylobanov.acrosstheuniverse.ui.MainActivity
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.chip.Chip


class PictureOfDayFragment : Fragment(R.layout.fragment_picture_of_day) {

    private val viewModel: PictureOfDayViewModel by lazy {
        ViewModelProviders.of(this).get(PictureOfDayViewModel::class.java)
    }

    lateinit var binding: FragmentPictureOfDayBinding

    companion object {
        private var isMain = true
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPictureOfDayBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
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
                    binding.dayBeforeYesterday -> viewModel.getDayBeforeYesterdayPictureOfDay()
                    binding.yesterday -> viewModel.getYesterdayPictureOfDay()
                    binding.today -> viewModel.getTodayPictureOfDay()
                }
            }
        }

    }

//    private fun setBottomAppBar(view: View) {
//        val context = activity as MainActivity
//        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
//        setHasOptionsMenu(true)
//        binding.fab.setOnClickListener {
//            if (isMain) {
//                isMain = false
//                binding.bottomAppBar.navigationIcon = null
//                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
//                binding.fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_back_24))
////                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
//            } else {
//                isMain = true
//                binding.bottomAppBar.navigationIcon =
//                        ContextCompat.getDrawable(context, R.drawable.ic_baseline_dehaze_24)
//                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
//                binding.fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_add_24))
//                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
//            }
//        }
//    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.menu_bottom_bar, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.app_bar_fav -> Toast.makeText(requireContext(), "Favourites", Toast.LENGTH_SHORT).show()
//            R.id.app_bar_settings -> this.findNavController()
//                    .navigate(R.id.action_photoOfDayFragment_to_settingsFragment)
//            android.R.id.home -> {
//                activity?.let {
////                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
//                }
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }


}