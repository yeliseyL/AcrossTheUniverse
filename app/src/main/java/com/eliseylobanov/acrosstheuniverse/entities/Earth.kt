package com.eliseylobanov.acrosstheuniverse.entities

import com.squareup.moshi.Json

data class Earth(@Json(name = "media_type")
                        val image: String,
                        val caption: String,
                        val date: String)