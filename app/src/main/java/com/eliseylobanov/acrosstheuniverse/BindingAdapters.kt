package com.eliseylobanov.acrosstheuniverse

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.eliseylobanov.acrosstheuniverse.entities.Photo
import com.eliseylobanov.acrosstheuniverse.ui.mars.MarsViewPagerAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Picasso.with(imgView.context)
            .load(imgUri)
            .placeholder(R.drawable.loading_animation)
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindViewPager(viewPager2: ViewPager2, data: List<Photo>?) {
    val adapter = viewPager2.adapter as MarsViewPagerAdapter
    adapter.submitList(data)
}