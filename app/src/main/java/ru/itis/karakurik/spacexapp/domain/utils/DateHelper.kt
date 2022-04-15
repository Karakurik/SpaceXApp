package ru.itis.karakurik.spacexapp.domain.utils

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {

    companion object {

        fun dateToTimestamp(date: Date): Long {
            return date.time
        }

        fun timestampToDate(timestamp: Long): Date {
            return Date(timestamp)
        }

        fun convertDateToString(date: Date): String {
            val dateFormat = SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault())
            return dateFormat.format(date)
        }

        fun convertTimeStampToString(timestamp: Long): String {
            val dateFormat = SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault())
            return dateFormat.format(Date(timestamp))
        }
    }
}
