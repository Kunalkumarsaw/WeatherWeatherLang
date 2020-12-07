package com.parungao.weatherweatherlang.Adapters

import android.content.Context
import com.parungao.weatherweatherlang.Models.WeatherData
import com.parungao.weatherweatherlang.Models.WeatherModel
import com.parungao.weatherweatherlang.R
import com.parungao.weatherweatherlang.Utilities.GetWeatherServices
import com.parungao.weatherweatherlang.Utilities.NetworkServiceBuilder
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call

@RunWith(MockitoJUnitRunner::class)
class WeatherAdapterTest {
    lateinit var weatherDataList : List<WeatherData>
    @Before
    fun setUp(){
        val getWeatherService = NetworkServiceBuilder.buildService(
            GetWeatherServices::class.java)
        val map : Map<String, String> = mapOf("appid" to "a7d39c34549f2a5dcc4a03dbdd6c51ba", "id" to "1701668,3067696,1835848")
        val requestCall: Call<WeatherModel> =  getWeatherService.getWeatherAndCitiesList(map)
        weatherDataList = requestCall.execute().body()!!.list
    }
    @Mock
    private lateinit var mockContext : Context

    @Test
    fun getItemCountTest(){
        `when`(mockContext.getString(R.string.appbar_scrolling_view_behavior))
            .thenReturn("")
       assertEquals("e Objects found", 3,  WeatherAdapter(context = mockContext, list = weatherDataList).itemCount)
    }
}