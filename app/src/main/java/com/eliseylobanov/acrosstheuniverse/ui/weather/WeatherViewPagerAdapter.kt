package com.eliseylobanov.acrosstheuniverse.ui.weather

import androidx.recyclerview.widget.DiffUtil
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.entities.WeatherItem
import com.eliseylobanov.acrosstheuniverse.ui.DataBindingAdapter

class WeatherViewPagerAdapter : DataBindingAdapter<WeatherItem>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<WeatherItem>() {
        override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
            return oldItem.messageID == newItem.messageID
        }
    }
    override fun getItemViewType(position: Int) = R.layout.weather_viewpager_item
}