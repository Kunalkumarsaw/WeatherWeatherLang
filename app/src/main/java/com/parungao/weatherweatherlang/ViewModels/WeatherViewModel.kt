package com.parungao.weatherweatherlang.ViewModels

import androidx.lifecycle.ViewModel
import com.parungao.weatherweatherlang.Models.WeatherRepository
import com.parungao.weatherweatherlang.Models.WeatherModel
import com.parungao.weatherweatherlang.Utilities.GetWeatherServices
import com.parungao.weatherweatherlang.Utilities.NetworkServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(private val weatherRepository: WeatherRepository ) : ViewModel() {
    fun getWeather() = weatherRepository.getWeather()
    fun updateWeather(weather: WeatherModel) = weatherRepository.updateWeather(weather)

    fun callOpenWeatherData(){
        val getWeatherService: GetWeatherServices = NetworkServiceBuilder.buildService(
            GetWeatherServices::class.java)
        val requestCall: Call<WeatherModel> =  getWeatherService.getWeatherAndCitiesList()
        requestCall.enqueue(object : Callback<WeatherModel> {
            override fun onResponse(
                call: Call<WeatherModel>,
                response: Response<WeatherModel>
            ) {
                val weatherAndCitiesObject: WeatherModel = response.body()!!
                updateWeather(weatherAndCitiesObject)
            }
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {}
        })
    }

}

