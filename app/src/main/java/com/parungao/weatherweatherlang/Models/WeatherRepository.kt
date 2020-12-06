package com.parungao.weatherweatherlang.Models

class WeatherRepository private constructor(private val weatherDao: FakeWeatherDao) {
    fun updateWeather(weather: WeatherModel){
        weatherDao.updateWeather(weather)
    }
    fun getWeather() = weatherDao.getWeather()
    companion object {
        @Volatile private var instance: WeatherRepository? = null
        fun getInstance(weatherDao: FakeWeatherDao) = instance ?: synchronized(lock = this) {
            instance ?: WeatherRepository(weatherDao).also { instance = it }
        }
    }
}