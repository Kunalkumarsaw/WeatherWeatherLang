package com.parungao.weatherweatherlang.ViewModels

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.parungao.weatherweatherlang.Models.WeatherData
import com.parungao.weatherweatherlang.Models.WeatherModel
import com.parungao.weatherweatherlang.Models.WeatherRepository
import com.parungao.weatherweatherlang.Utilities.GetWeatherOfCityServices
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
    fun setCityObject(cityObject: WeatherData) = weatherRepository.setCityObject(cityObject)
    fun getCityObject() = weatherRepository.getCityObject()

    fun callOpenWeatherData(){
        val getWeatherService: GetWeatherServices = NetworkServiceBuilder.buildService(
            GetWeatherServices::class.java)
        val map : Map<String, String> = mapOf("appid" to "a7d39c34549f2a5dcc4a03dbdd6c51ba", "id" to "1701668,3067696,1835848")
        val requestCall: Call<WeatherModel> =  getWeatherService.getWeatherAndCitiesList(map)
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
    fun callOpenWeatherForCitiesData(id : String){
        val getWeatherService: GetWeatherOfCityServices = NetworkServiceBuilder.buildService(
            GetWeatherOfCityServices::class.java)
        val map : Map<String, String> = mapOf("appid" to "a7d39c34549f2a5dcc4a03dbdd6c51ba", "id" to id)

        val requestCall: Call<WeatherData> =  getWeatherService.getWeatherOfCityServicesObject(map)
        requestCall.enqueue(object : Callback<WeatherData> {
            override fun onResponse(
                call: Call<WeatherData>,
                response: Response<WeatherData>
            ) {
                if (response.isSuccessful){
                    val weatherAndCitiesObject: WeatherData = response.body()!!
                    setCityObject(weatherAndCitiesObject)
                }else{
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    setError(jObjError.toString())
                }
            }
            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                setError(t.toString())
            }
        })
    }

}

