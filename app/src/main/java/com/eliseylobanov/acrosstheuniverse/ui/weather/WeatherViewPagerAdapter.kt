package com.eliseylobanov.acrosstheuniverse.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eliseylobanov.acrosstheuniverse.databinding.WeatherViewpagerItemBinding
import com.eliseylobanov.acrosstheuniverse.entities.WeatherItem

class WeatherViewPagerAdapter : ListAdapter<WeatherItem, WeatherViewPagerAdapter.WeatherViewHolder>(DiffCallback) {

    class WeatherViewHolder(private var binding: WeatherViewpagerItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: WeatherItem) {
            binding.weather = weather
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<WeatherItem>() {
        override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
            return oldItem.messageID == newItem.messageID
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(WeatherViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = getItem(position)
        holder.bind(weather)
    }
}