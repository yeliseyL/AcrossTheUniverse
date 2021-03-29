package com.eliseylobanov.acrosstheuniverse.ui.mars

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eliseylobanov.acrosstheuniverse.BuildConfig
import com.eliseylobanov.acrosstheuniverse.entities.Earth
import com.eliseylobanov.acrosstheuniverse.entities.Mars
import com.eliseylobanov.acrosstheuniverse.entities.Photo
import com.eliseylobanov.acrosstheuniverse.getYesterday
import com.eliseylobanov.acrosstheuniverse.network.NASAApi
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MarsViewModel: ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _marsUrl = MutableLiveData<String>()
    val marsUrl: LiveData<String>
        get() = _marsUrl

    private val _mars = MutableLiveData<Mars>()
    val mars: LiveData<Mars>
        get() = _mars

    init {
        getMarsPicture()
    }

    private fun getMarsPicture() {
        viewModelScope.launch {
            try {
                val mars = NASAApi.retrofitEarthService.getMars(getYesterday(),
                        BuildConfig.API_KEY)
                _mars.value = mars
                _marsUrl.value = mars.photos[0].img_src
                _photos.value = mars.photos
            } catch (ex: UnknownHostException) {
                Log.e("PictureOfDay", "Network error")
            }
        }
    }
}