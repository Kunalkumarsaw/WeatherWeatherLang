package com.parungao.weatherweatherlang.Models
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeWeatherDao {
    private val weather = MutableLiveData<WeatherModel>()
    private val errorMsg = MutableLiveData<String>()
    private val city = MutableLiveData<WeatherData>()
    init{ }
    fun updateWeather(weatherValue: WeatherModel){
        weather.value = weatherValue
    }
    fun getWeather() = weather as LiveData<WeatherModel>
    fun setCityObject(cityValue: WeatherData){
        city.value = cityValue
    }
    fun getCityObject() = city as LiveData<WeatherData>
    fun setError(errorValue: String){
        errorMsg.value = errorValue
    }
    fun getError() = errorMsg as LiveData<String>

}