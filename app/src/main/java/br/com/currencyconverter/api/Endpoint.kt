package br.com.currencyconverter.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {

    //Buscar a Taxa de Câmbio na API do Banco Central: https://cdn.moeda.info/api/bcb.json
    @GET("/api/bcb.json")
    fun getExchangeRateBcb(): Call<JsonObject>

    //Buscar a Taxa de Câmbio na API de taxas de câmbio: https://cdn.moeda.info/api/latest.json
    @GET("/api/latest.json")
    fun getExchangeRate(): Call<JsonObject>

}