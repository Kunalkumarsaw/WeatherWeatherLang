package com.parungao.weatherweatherlang.ViewModels

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.parungao.weatherweatherlang.Models.WeatherModel
import com.parungao.weatherweatherlang.Models.WeatherRepository
import com.parungao.weatherweatherlang.Utilities.GetWeatherServices
import com.parungao.weatherweatherlang.Utilities.NetworkServiceBuilder
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherViewModel(private val weatherRepository: WeatherRepository ) : ViewModel() {
    fun getWeather() = weatherRepository.getWeather()
    fun updateWeather(weather: WeatherModel) = weatherRepository.updateWeather(weather)
    fun setError(errorValue: String) = weatherRepository.setError(errorValue)
    fun getError() = weatherRepository.getError()

    fun callOpenWeatherData(){
        val getWeatherService: GetWeatherServices = NetworkServiceBuilder.buildService(
            GetWeatherServices::class.java)
        val requestCall: Call<WeatherModel> =  getWeatherService.getWeatherAndCitiesList()
        requestCall.enqueue(object : Callback<WeatherModel> {
            override fun onResponse(
                call: Call<WeatherModel>,
                response: Response<WeatherModel>
            ) {
                if (response.isSuccessful){
                    val weatherAndCitiesObject: WeatherModel = response.body()!!
                    updateWeather(weatherAndCitiesObject)
                }else{
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    setError(jObjError.toString())
                }
            }
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                setError(t.toString())
            }
        })
    }

}

