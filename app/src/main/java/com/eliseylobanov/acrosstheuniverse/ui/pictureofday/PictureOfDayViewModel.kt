package com.eliseylobanov.acrosstheuniverse.ui.pictureofday

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eliseylobanov.acrosstheuniverse.ApiStatus
import com.eliseylobanov.acrosstheuniverse.BuildConfig
import com.eliseylobanov.acrosstheuniverse.entities.PictureOfDay
import com.eliseylobanov.acrosstheuniverse.network.NASAApi
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class PictureOfDayViewModel: ViewModel() {
    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    init {
        getTodayPictureOfDay()
    }

    fun getTodayPictureOfDay() {
        viewModelScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                val picture = NASAApi.retrofitPictureService.getTodayPictureOfTheDay(BuildConfig.API_KEY)
                _pictureOfDay.value = picture
                _status.value = ApiStatus.DONE
            } catch (ex: UnknownHostException) {
                Log.e("PictureOfDay", "Network error")
                _status.value = ApiStatus.ERROR
            }
        }
    }

    fun getPictureOfDay(date: String) {
        viewModelScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                val picture = NASAApi.retrofitPictureService.getPictureOfTheDay(date,
                        BuildConfig.API_KEY)
                _pictureOfDay.value = picture
                _status.value = ApiStatus.DONE
            } catch (ex: UnknownHostException) {
                Log.e("PictureOfDay", "Network error")
                _status.value = ApiStatus.ERROR
            }
        }
    }
}
