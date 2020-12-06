package com.parungao.weatherweatherlang.Utilities

import com.parungao.weatherweatherlang.Models.WeatherData
import com.parungao.weatherweatherlang.Models.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface GetWeatherServices {
    @GET("data/2.5/group")
    fun getWeatherAndCitiesList(@QueryMap() map: Map<String, String>): Call<WeatherModel>

}

interface GetWeatherOfCityServices {
    val city : String

    @GET("data/2.5/weather")
    fun getWeatherOfCityServicesObject(@QueryMap() map: Map<String, String> ): Call<WeatherData>
}