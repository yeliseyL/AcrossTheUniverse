package com.eliseylobanov.acrosstheuniverse

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.viewpager2.widget.ViewPager2
import java.text.SimpleDateFormat
import java.util.*

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
