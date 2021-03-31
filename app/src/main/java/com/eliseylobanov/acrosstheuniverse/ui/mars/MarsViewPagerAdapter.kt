package com.eliseylobanov.acrosstheuniverse.ui.mars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.databinding.MarsPhotoViewpagerItemBinding
import com.eliseylobanov.acrosstheuniverse.entities.Earth
import com.eliseylobanov.acrosstheuniverse.entities.Photo
import com.eliseylobanov.acrosstheuniverse.ui.DataBindingAdapter

//class MarsViewPagerAdapter: ListAdapter<Photo, MarsViewPagerAdapter.PhotoViewHolder>(DiffCallback) {
//
//    class PhotoViewHolder(private var binding: MarsPhotoViewpagerItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(photo: Photo) {
//            binding.photo = photo
//            binding.executePendingBindings()
//        }
//    }
//
//    companion object DiffCallback : DiffUtil.ItemCallback<Photo>() {
//        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
//            return oldItem === newItem
//        }
//
//        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
//            return oldItem.id == newItem.id
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup,
//                                    viewType: Int): PhotoViewHolder {
//        return PhotoViewHolder(MarsPhotoViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//    }
//
//    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
//        val photo = getItem(position)
//        holder.bind(photo)
//    }
//}

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