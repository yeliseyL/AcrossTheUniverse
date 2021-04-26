package com.eliseylobanov.acrosstheuniverse.entities

data class WeatherItem(
    val messageBody: String,
    val messageID: String,
    val messageIssueTime: String,
    val messageType: String,
    val messageURL: String
)