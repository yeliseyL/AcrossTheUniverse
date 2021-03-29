package com.eliseylobanov.acrosstheuniverse.ui.earth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eliseylobanov.acrosstheuniverse.BuildConfig
import com.eliseylobanov.acrosstheuniverse.entities.Earth
import com.eliseylobanov.acrosstheuniverse.network.NASAApi
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class EarthViewModel: ViewModel() {

    private val _earth = MutableLiveData<List<Earth>>()
    val earth: LiveData<List<Earth>>
        get() = _earth

    init {
        getEarth()
    }

    private fun getEarth() {
        viewModelScope.launch {
            try {
                val earth = NASAApi.retrofitEarthService.getEarth(
                    BuildConfig.API_KEY)
                _earth.value = earth
            } catch (ex: UnknownHostException) {
                Log.e("PictureOfDay", "Network error")
            }
        }
    }
}