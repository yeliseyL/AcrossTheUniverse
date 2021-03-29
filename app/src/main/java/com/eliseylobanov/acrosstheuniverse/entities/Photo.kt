package com.eliseylobanov.acrosstheuniverse.entities

data class Photo(
    val camera: Camera,
    val earth_date: String,
    val img_src: String,
    val rover: Rover
)