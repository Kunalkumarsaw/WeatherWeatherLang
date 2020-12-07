package com.parungao.weatherweatherlang.Models

class WeatherRepository(private val weatherDao: FakeWeatherDao) {
    fun updateWeather(weather: WeatherModel){
        weatherDao.updateWeather(weather)
    }
    fun getWeather() = weatherDao.getWeather()

    fun setCityObject(cityObject: WeatherData){
        weatherDao.setCityObject(cityObject)
    }
    fun getCityObject() = weatherDao.getCityObject()


    fun setError(errorValue: String){
        weatherDao.setError(errorValue)
    }
    fun getError() = weatherDao.getError()
    companion object {
        @Volatile private var instance: WeatherRepository? = null
        fun getInstance(weatherDao: FakeWeatherDao) = instance ?: synchronized(lock = this) {
            instance ?: WeatherRepository(weatherDao).also { instance = it }
        }
    }
}