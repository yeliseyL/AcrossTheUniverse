package com.eliseylobanov.acrosstheuniverse.ui.earth

import androidx.recyclerview.widget.DiffUtil
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.entities.Earth
import com.eliseylobanov.acrosstheuniverse.ui.DataBindingAdapter

class EarthViewPagerAdapter : DataBindingAdapter<Earth>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Earth>() {
        override fun areItemsTheSame(oldItem: Earth, newItem: Earth): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Earth, newItem: Earth): Boolean {
            return oldItem.identifier == newItem.identifier
        }
    }
    override fun getItemViewType(position: Int) = R.layout.earth_viewpager_item
}