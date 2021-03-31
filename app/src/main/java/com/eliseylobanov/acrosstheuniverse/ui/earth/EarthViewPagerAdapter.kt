package com.eliseylobanov.acrosstheuniverse.ui.earth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.databinding.EarthViewpagerItemBinding
import com.eliseylobanov.acrosstheuniverse.entities.Earth
import com.eliseylobanov.acrosstheuniverse.ui.DataBindingAdapter

//class EarthViewPagerAdapter: ListAdapter<Earth, EarthViewPagerAdapter.EarthViewHolder>(DiffCallback) {
//
//    class EarthViewHolder(private var binding: EarthViewpagerItemBinding) :
//            RecyclerView.ViewHolder(binding.root) {
//        fun bind(earth: Earth) {
//            binding.earth = earth
//            binding.executePendingBindings()
//        }
//    }
//
//    companion object DiffCallback : DiffUtil.ItemCallback<Earth>() {
//        override fun areItemsTheSame(oldItem: Earth, newItem: Earth): Boolean {
//            return oldItem === newItem
//        }
//
//        override fun areContentsTheSame(oldItem: Earth, newItem: Earth): Boolean {
//            return oldItem.identifier == newItem.identifier
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup,
//                                    viewType: Int): EarthViewHolder {
//        return EarthViewHolder(EarthViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//    }
//
//    override fun onBindViewHolder(holder: EarthViewHolder, position: Int) {
//        val earth = getItem(position)
//        holder.bind(earth)
//    }
//}

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