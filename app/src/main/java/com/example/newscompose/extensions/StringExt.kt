package com.example.newscompose.extensions

import java.text.SimpleDateFormat

fun String.toDomainDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val outputFormat = SimpleDateFormat("yyyy-MM-dd")
    return outputFormat.format(inputFormat.parse(this))
}

fun String.isBetweenDates(start: String, end: String): Boolean {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val now = dateFormat.parse(this)
    val before = dateFormat.parse(start)
    val after = dateFormat.parse(end)

    return (now == before || now == after || (now.after(before) && now.before(after)))
}
