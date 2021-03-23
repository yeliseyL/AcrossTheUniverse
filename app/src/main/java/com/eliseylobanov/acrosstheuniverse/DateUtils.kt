package com.eliseylobanov.acrosstheuniverse

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

fun getYesterday(): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, -1)
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(calendar.time)
}
fun getDayBeforeYesterday(): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, -2)
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(calendar.time)
}
