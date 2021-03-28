package com.eliseylobanov.acrosstheuniverse.ui.earth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eliseylobanov.acrosstheuniverse.BuildConfig
import com.eliseylobanov.acrosstheuniverse.entities.Earth
import com.eliseylobanov.acrosstheuniverse.entities.PictureOfDay
import com.eliseylobanov.acrosstheuniverse.getYesterday
import com.eliseylobanov.acrosstheuniverse.network.NASAApi
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class EarthViewModel: ViewModel() {

    private val date = getYesterday().split("-")

    private val _earthUrl = MutableLiveData<String>()
    val earthUrl: LiveData<String>
        get() = _earthUrl

    private val _earthPicture = MutableLiveData<Earth>()
    val earthPicture: LiveData<Earth>
        get() = _earthPicture

    init {
        getEarthPicture()
    }

    private fun getEarthPicture() {
        viewModelScope.launch {
            try {
                _earthPicture.value = NASAApi.retrofitEarthService.getEarth(
                    getYesterday(),
                    BuildConfig.API_KEY)[0]
                _earthUrl.value =
                    "https://api.nasa.gov/EPIC/archive/natural/${date[0]}/${date[1]}/${date[2]}" +
                            "/png/${earthPicture.value?.image}.png?api_key=${BuildConfig.API_KEY}"
            } catch (ex: UnknownHostException) {
                Log.e("PictureOfDay", "Network error")
            }
        }
    }
}