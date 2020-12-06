package com.parungao.weatherweatherlang.Models
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeWeatherDao {
    private val weather = MutableLiveData<WeatherModel>()
    init{ }
    fun updateWeather(weatherValue: WeatherModel){
        weather.value = weatherValue
    }
    fun getWeather() = weather as LiveData<WeatherModel>
}