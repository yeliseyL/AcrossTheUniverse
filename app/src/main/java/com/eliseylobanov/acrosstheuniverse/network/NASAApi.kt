package com.eliseylobanov.acrosstheuniverse.network

import com.eliseylobanov.acrosstheuniverse.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NASAApi {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofitPicture = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitEarth = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitPictureService: NASAApiPictureService = retrofitPicture.create(NASAApiPictureService::class.java)
    val retrofitEarthService: NASAApiPictureService = retrofitEarth.create(NASAApiPictureService::class.java)
}