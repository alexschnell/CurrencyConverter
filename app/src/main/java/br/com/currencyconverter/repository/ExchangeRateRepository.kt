package br.com.currencyconverter.repository

import java.util.Date

interface ExchangeRateRepository {
    suspend fun getExchangeRates(): Pair<List<Pair<String, Double>>, Date?>
}