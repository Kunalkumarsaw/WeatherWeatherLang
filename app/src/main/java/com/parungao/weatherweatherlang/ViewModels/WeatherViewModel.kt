package com.parungao.weatherweatherlang.ViewModels

import androidx.lifecycle.ViewModel
import com.parungao.weatherweatherlang.Models.WeatherRepository
import com.parungao.weatherweatherlang.Models.WeatherModel

class WeatherViewModel(private val weatherRepository: WeatherRepository ) : ViewModel() {
    fun getWeather() = weatherRepository.getWeather()
    fun updateWeather(weather: WeatherModel) = weatherRepository.updateWeather(weather)
}