package com.parungao.weatherweatherlang.ViewModels

import org.junit.After
import org.junit.Before
import com.parungao.weatherweatherlang.Models.FakeWeatherDao
import com.parungao.weatherweatherlang.Models.WeatherModel
import com.parungao.weatherweatherlang.Models.WeatherRepository
import com.parungao.weatherweatherlang.Utilities.GetWeatherServices
import com.parungao.weatherweatherlang.Utilities.NetworkServiceBuilder
import org.junit.Test
import retrofit2.Call
import org.junit.Assert.assertEquals

class WeatherViewModelTest {

    @Before
    fun setUp() {
    }
    @Test
    fun callOpenWeatherDataTest(){
        WeatherViewModel(WeatherRepository(FakeWeatherDao())).callOpenWeatherData()
    }
    @Test
    fun getWeatherAndCitiesListTest(){
        val getWeatherService = NetworkServiceBuilder.buildService(
            GetWeatherServices::class.java)
        val map : Map<String, String> = mapOf("appid" to "a7d39c34549f2a5dcc4a03dbdd6c51ba", "id" to "1701668,3067696,1835848")
        val requestCall: Call<WeatherModel> =  getWeatherService.getWeatherAndCitiesList(map)
        val result = requestCall.execute().body()
        assertEquals("Found 3 items",3,result!!.cnt)
    }
    @After
    fun tearDown() {
    }
}