package br.com.xpapi.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    private val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)
    private val outputDayMonthFormat = SimpleDateFormat("dd/MM")
    private val outputDayMonthYearFormat = SimpleDateFormat("dd/MM/yyyy")

    fun formatToDayMonthDate(dateAsString: String): String {
        val date = inputDateFormat.parse(dateAsString)
        return outputDayMonthFormat.format(date)
    }

    fun formatToDayMonthYearDate(dateAsString: String): String {
        val date = inputDateFormat.parse(dateAsString)
        return outputDayMonthYearFormat.format(date)
    }

    fun formatToDate(dateAsString: String): Date {
        return inputDateFormat.parse(dateAsString)
    }
}