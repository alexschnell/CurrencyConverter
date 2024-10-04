package br.com.currencyconverter.util

import java.text.SimpleDateFormat
import java.util.Date

fun formatDate(data: Date?): String {
    // Define o formato da data
    val formato = SimpleDateFormat("dd/MM/yyyy")
    // Retorna a data formatada ou "" caso seja nula
    return data?.let { formato.format(it) } ?: ""
}

fun formatDateHour(data: Date?): String {
    val formato = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    return data?.let { formato.format(it) } ?: ""
}