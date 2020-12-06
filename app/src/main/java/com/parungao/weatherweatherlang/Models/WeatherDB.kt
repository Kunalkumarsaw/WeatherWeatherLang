package com.parungao.weatherweatherlang.Models

class WeatherDB private constructor() {
    var weatherDao = FakeWeatherDao()
        private set
    companion object {
        @Volatile private var instance: WeatherDB? = null
        fun getInstance() = instance ?: synchronized(lock = this) {
            instance ?: WeatherDB().also { instance = it }
        }
    }
}