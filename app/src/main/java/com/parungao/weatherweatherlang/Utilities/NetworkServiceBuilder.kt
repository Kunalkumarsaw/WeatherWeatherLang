package com.parungao.weatherweatherlang.Utilities

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceBuilder {
    private const val URL = "https://api.openweathermap.org/"
    private val okHttp: OkHttpClient.Builder = OkHttpClient.Builder()
    private val builder: Retrofit.Builder = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).client(
        okHttp.build())
    private val retrofit: Retrofit = builder.build()
    fun <T> buildService(serviceType: Class<T>): T{
        return retrofit.create(serviceType)
    }
}