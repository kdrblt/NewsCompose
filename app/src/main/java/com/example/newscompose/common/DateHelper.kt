package com.example.newscompose.common

import java.text.SimpleDateFormat
import java.util.Calendar

object DateHelper {

    var fromDate = getTenDaysAgoDate()
    var toDate = getCurrentDate()

    fun getCurrentDate(): String {
        val cal: Calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat(API_DATE_FORMAT)
        return formatter.format(cal.time)
    }

    fun getTenDaysAgoDate(): String {
        val cal: Calendar = Calendar.getInstance()
        val currentDay: Int = cal.get(Calendar.DAY_OF_MONTH)
        cal.set(Calendar.DAY_OF_MONTH, currentDay - 10)
        val formatter = SimpleDateFormat(API_DATE_FORMAT)
        return formatter.format(cal.time)
    }

    fun convertLongToDate(longDate: Long): String {
        val simpleDateFormat = SimpleDateFormat(API_DATE_FORMAT)
        return simpleDateFormat.format(longDate)
    }

    private const val API_DATE_FORMAT = "yyyy-MM-dd"
}
