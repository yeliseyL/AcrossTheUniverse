package com.eliseylobanov.acrosstheuniverse.network

import com.eliseylobanov.acrosstheuniverse.entities.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query

interface NASAApiPictureService {
    @GET("planetary/apod")
    suspend fun getTodayPictureOfTheDay(@Query("api_key") key: String): PictureOfDay

    @GET("planetary/apod")
    suspend fun getPictureOfTheDay(@Query("date") date: String, @Query("api_key") key: String): PictureOfDay
}