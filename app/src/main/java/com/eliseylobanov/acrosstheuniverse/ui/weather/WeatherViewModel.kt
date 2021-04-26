package com.eliseylobanov.acrosstheuniverse.ui.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eliseylobanov.acrosstheuniverse.ApiStatus
import com.eliseylobanov.acrosstheuniverse.BuildConfig
import com.eliseylobanov.acrosstheuniverse.entities.WeatherItem
import com.eliseylobanov.acrosstheuniverse.getDayBeforeYesterday
import com.eliseylobanov.acrosstheuniverse.getYesterday
import com.eliseylobanov.acrosstheuniverse.network.NASAApi
import kotlinx.coroutines.launch
import java.net.UnknownHostException

const val TYPE = "all"

class WeatherViewModel: ViewModel() {

    private val _weather = MutableLiveData<List<WeatherItem>>()
    val weather: LiveData<List<WeatherItem>>
        get() = _weather

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    init {
        getWeather()
    }

    private fun getWeather() {
        viewModelScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                val weatherResult = NASAApi.retrofitEarthService.getWeather(
                        getYesterday(),
                        getDayBeforeYesterday(),
                        TYPE,
                        BuildConfig.API_KEY)
                _weather.value = weatherResult
                _status.value = ApiStatus.DONE
            } catch (ex: UnknownHostException) {
                Log.e("PictureOfDay", "Network error")
                _status.value = ApiStatus.ERROR
            }
        }
    }
}