package com.eliseylobanov.acrosstheuniverse.entities

import com.squareup.moshi.Json

data class Mars(@Json(name = "media_type")
    val photos: List<Photo>
)