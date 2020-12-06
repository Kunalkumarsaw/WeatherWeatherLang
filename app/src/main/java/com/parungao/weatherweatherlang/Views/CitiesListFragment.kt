package com.parungao.weatherweatherlang.Views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.parungao.weatherweatherlang.R
import com.parungao.weatherweatherlang.Utilities.InjectorUtils
import com.parungao.weatherweatherlang.ViewModels.WeatherViewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.parungao.weatherweatherlang.Adapters.WeatherAdapter
import com.parungao.weatherweatherlang.Models.WeatherModel
import com.parungao.weatherweatherlang.Utilities.GetWeatherServices
import com.parungao.weatherweatherlang.Utilities.NetworkServiceBuilder
import com.parungao.weatherweatherlang.ViewModels.WeatherViewModelFactory
import kotlinx.android.synthetic.main.fragment_cities_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CitiesListFragment : Fragment() {
//    val w : WeatherViewModelFactory
//    val x : WeatherViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_cities_list, container,
            false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_first.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        initializeUi()
        initializeObservers()
    }
    private fun initializeUi(){
        val activity = activity as Context
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
    }
    private fun initializeObservers(){
        val factory = InjectorUtils.provideWeatherViewModelFactory()
        val viewModel = ViewModelProviders.of(this , factory).get(WeatherViewModel::class.java)
        viewModel.getWeather().observe(this, Observer { weatherAndCitiesObject ->
            recyclerView.adapter = WeatherAdapter(activity as Context, weatherAndCitiesObject.list)
        })
        addButton.setOnClickListener{
            callOpenWeatherData(viewModel)
        }
        callOpenWeatherData(viewModel)
    }
    private fun callOpenWeatherData(viewModel : WeatherViewModel){
        val getWeatherService: GetWeatherServices = NetworkServiceBuilder.buildService(GetWeatherServices::class.java)
        val requestCall: Call<WeatherModel> =  getWeatherService.getWeatherAndCitiesList()
        requestCall.enqueue(object : Callback<WeatherModel>{
            override fun onResponse(
                call: Call<WeatherModel>,
                response: Response<WeatherModel>
            ) {
                val weatherAndCitiesObject: WeatherModel = response.body()!!
                viewModel.updateWeather(weatherAndCitiesObject)
            }
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {}
        })
    }
}