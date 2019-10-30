package br.com.xpapi.util

import java.text.NumberFormat
import java.util.Locale

private val brazilLocale = Locale("pt", "BR")

fun Double.iwsFormat() = NumberFormat.getNumberInstance(brazilLocale).apply {
    minimumFractionDigits = 2
}.format(this)

fun String.toDoubleXP() = this
        .replace("R$ ", "")
        .replace(".","")
        .replace(",",".")
        .toDouble()