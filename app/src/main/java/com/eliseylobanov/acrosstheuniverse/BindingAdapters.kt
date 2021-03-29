package com.eliseylobanov.acrosstheuniverse

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.eliseylobanov.acrosstheuniverse.entities.Earth
import com.eliseylobanov.acrosstheuniverse.entities.Photo
import com.eliseylobanov.acrosstheuniverse.entities.WeatherItem
import com.eliseylobanov.acrosstheuniverse.ui.earth.EarthViewPagerAdapter
import com.eliseylobanov.acrosstheuniverse.ui.mars.MarsViewPagerAdapter
import com.eliseylobanov.acrosstheuniverse.ui.weather.WeatherViewPagerAdapter
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

@BindingAdapter("earthImageUrl")
fun bindEarthImage(imgView: ImageView, earth: Earth?) {
    val date = earth?.date?.split(" ")?.get(0)?.split("-")
    val imgUrl = "https://api.nasa.gov/EPIC/archive/natural/${date?.get(0)}/${date?.get(1)}/${date?.get(2)}" +
                            "/png/${earth?.image}.png?api_key=${BuildConfig.API_KEY}"
    imgUrl.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Picasso.with(imgView.context)
                .load(imgUri)
                .placeholder(R.drawable.loading_animation)
                .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindMarsViewPager(viewPager: ViewPager2, data: List<Photo>?) {
    val adapter = viewPager.adapter as MarsViewPagerAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindEarthViewPager(viewPager: ViewPager2, data: List<Earth>?) {
    val adapter = viewPager.adapter as EarthViewPagerAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindWeatherViewPager(viewPager: ViewPager2, data: List<WeatherItem>?) {
    val adapter = viewPager.adapter as WeatherViewPagerAdapter
    adapter.submitList(data)
}