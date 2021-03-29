package com.eliseylobanov.acrosstheuniverse.network

import com.eliseylobanov.acrosstheuniverse.entities.Earth
import com.eliseylobanov.acrosstheuniverse.entities.Mars
import com.eliseylobanov.acrosstheuniverse.entities.PictureOfDay
import com.eliseylobanov.acrosstheuniverse.entities.Weather
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NASAApiPictureService {
    @GET("planetary/apod")
    suspend fun getTodayPictureOfTheDay(@Query("api_key") key: String): PictureOfDay

    @GET("planetary/apod")
    suspend fun getPictureOfTheDay(@Query("date") date: String,
                                   @Query("api_key") key: String): PictureOfDay

    @GET("EPIC/api/natural/images")
    suspend fun getEarth(@Query("api_key") key: String): List<Earth>

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getMars(@Query("earth_date") date: String,
                        @Query("api_key") key: String): Mars

    @GET("DONKI/notifications")
    suspend fun getWeather(@Query("startDate") startDate: String,
                           @Query("endDate") endDate: String,
                           @Query("type") type: String,
                           @Query("api_key") key: String): Weather
}