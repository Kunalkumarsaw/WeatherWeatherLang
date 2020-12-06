package com.parungao.weatherweatherlang.Models
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeWeatherDao {
    private val weather = MutableLiveData<WeatherModel>()
    private val errorMsg = MutableLiveData<String>()
    init{ }
    fun updateWeather(weatherValue: WeatherModel){
        weather.value = weatherValue
    }
    fun getWeather() = weather as LiveData<WeatherModel>
    fun setError(errorValue: String){
        errorMsg.value = errorValue
    }
    fun getError() = errorMsg as LiveData<String>

}