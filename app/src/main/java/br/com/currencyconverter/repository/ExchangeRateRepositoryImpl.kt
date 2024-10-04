package br.com.currencyconverter.repository

import br.com.currencyconverter.api.Endpoint
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ExchangeRateRepositoryImpl(private val api: Endpoint): ExchangeRateRepository {
    override suspend fun getExchangeRates(): Pair<List<Pair<String, Double>>, Date?> {
        return suspendCoroutine { continuation ->
            api.getExchangeRate().enqueue(object : retrofit2.Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful) {
                        response.body()?.let { jsonBody ->
                            val ratesObject = jsonBody.getAsJsonObject("rates")
                            val ratesList = ratesObject.keySet().map { key->
                                Pair(key, ratesObject.get(key).asDouble)
                            }.sortedBy { it.first }

                            val lastUpdateString = jsonBody.get("lastupdate").asString
                            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
                            val lastUpdateDate = formatter.parse(lastUpdateString)

                            continuation.resume(Pair(ratesList, lastUpdateDate))
                        } ?: run {
                            continuation.resume(Pair(emptyList(), null))
                        }
                    } else {
                        println("Erro na resposta: ${response.message()}")
                        continuation.resume(Pair(emptyList(), null))
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    println("Falha na solicitação: ${t.message}")
                    continuation.resume(Pair(emptyList(), null))
                }
            })
        }
    }
}