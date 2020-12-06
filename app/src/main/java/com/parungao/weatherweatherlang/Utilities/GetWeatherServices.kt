package com.parungao.weatherweatherlang.Utilities

import com.parungao.weatherweatherlang.Models.WeatherModel
import retrofit2.Call
import retrofit2.http.GET

interface GetWeatherServices {
    @GET("data/2.5/group?id=1701668,3067696,1835848&appid=a7d39c34549f2a5dcc4a03dbdd6c51ba/")
    fun getWeatherAndCitiesList(): Call<WeatherModel>
}