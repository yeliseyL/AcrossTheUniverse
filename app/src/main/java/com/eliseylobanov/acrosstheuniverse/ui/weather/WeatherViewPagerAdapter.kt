package com.eliseylobanov.acrosstheuniverse.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.databinding.WeatherViewpagerItemBinding
import com.eliseylobanov.acrosstheuniverse.entities.Photo
import com.eliseylobanov.acrosstheuniverse.entities.WeatherItem
import com.eliseylobanov.acrosstheuniverse.ui.DataBindingAdapter

//class WeatherViewPagerAdapter : ListAdapter<WeatherItem, WeatherViewPagerAdapter.WeatherViewHolder>(DiffCallback) {
//
//    class WeatherViewHolder(private var binding: WeatherViewpagerItemBinding) :
//            RecyclerView.ViewHolder(binding.root) {
//        fun bind(weather: WeatherItem) {
//            binding.weather = weather
//            binding.executePendingBindings()
//        }
//    }
//
//    companion object DiffCallback : DiffUtil.ItemCallback<WeatherItem>() {
//        override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
//            return oldItem === newItem
//        }
//
//        override fun areContentsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
//            return oldItem.messageID == newItem.messageID
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup,
//                                    viewType: Int): WeatherViewHolder {
//        return WeatherViewHolder(WeatherViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//    }
//
//    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
//        val weather = getItem(position)
//        holder.bind(weather)
//    }
//}

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