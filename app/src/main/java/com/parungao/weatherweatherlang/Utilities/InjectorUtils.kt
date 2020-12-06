package com.parungao.weatherweatherlang.Utilities

import com.parungao.weatherweatherlang.Models.WeatherDB
import com.parungao.weatherweatherlang.Models.WeatherRepository
import com.parungao.weatherweatherlang.ViewModels.WeatherViewModelFactory

object InjectorUtils {
    fun provideWeatherViewModelFactory(): WeatherViewModelFactory{
        val weatherRepository = WeatherRepository.getInstance(WeatherDB.getInstance().weatherDao)
        return WeatherViewModelFactory(weatherRepository)
    }
}