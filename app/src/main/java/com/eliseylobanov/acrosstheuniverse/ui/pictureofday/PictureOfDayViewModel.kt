package com.eliseylobanov.acrosstheuniverse.ui.pictureofday

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eliseylobanov.acrosstheuniverse.ApiStatus
import com.eliseylobanov.acrosstheuniverse.BuildConfig
import com.eliseylobanov.acrosstheuniverse.entities.PictureOfDay
import com.eliseylobanov.acrosstheuniverse.getDayBeforeYesterday
import com.eliseylobanov.acrosstheuniverse.getYesterday
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
            _status.value = ApiStatus.LOADING
            try {
                _pictureOfDay.value = NASAApi.retrofitPictureService.getTodayPictureOfTheDay(BuildConfig.API_KEY)
                _status.value = ApiStatus.DONE
            } catch (ex: UnknownHostException) {
                Log.e("PictureOfDay", "Network error")
                _status.value = ApiStatus.ERROR
            }
        }
    }

    fun getYesterdayPictureOfDay() {
        viewModelScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                _pictureOfDay.value = NASAApi.retrofitPictureService.getPictureOfTheDay(getYesterday(),
                        BuildConfig.API_KEY)
                _status.value = ApiStatus.DONE
            } catch (ex: UnknownHostException) {
                Log.e("PictureOfDay", "Network error")
                _status.value = ApiStatus.ERROR
            }
        }
    }

    fun getDayBeforeYesterdayPictureOfDay() {
        viewModelScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                _pictureOfDay.value = NASAApi.retrofitPictureService.getPictureOfTheDay(
                        getDayBeforeYesterday(), BuildConfig.API_KEY)
                _status.value = ApiStatus.DONE
            } catch (ex: UnknownHostException) {
                Log.e("PictureOfDay", "Network error")
                _status.value = ApiStatus.ERROR
            }
        }
    }
}
