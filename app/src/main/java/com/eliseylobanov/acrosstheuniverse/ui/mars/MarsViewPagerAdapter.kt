package com.eliseylobanov.acrosstheuniverse.ui.mars

import androidx.recyclerview.widget.DiffUtil
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.entities.Photo
import com.eliseylobanov.acrosstheuniverse.ui.DataBindingAdapter

class MarsViewPagerAdapter : DataBindingAdapter<Photo>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }
    }
    override fun getItemViewType(position: Int) = R.layout.mars_photo_viewpager_item
}