package com.eliseylobanov.acrosstheuniverse.repository

import com.eliseylobanov.acrosstheuniverse.entities.PictureOfDay

class FakePictureRepository {
    suspend fun getTodayPictureOfTheDay(): PictureOfDay {
        return PictureOfDay("image", "Mars", "url", "planet")
    }
}