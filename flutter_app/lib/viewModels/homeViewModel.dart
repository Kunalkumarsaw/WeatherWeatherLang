import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:provider_architecture/_base_viewmodels.dart';
import '../helpers/weatherServices.dart';
import '../helpers/weatherProvider.dart';

class HomeViewModel extends BaseViewModel {
  void getWeatherList(BuildContext context) async{
    var weatherData = await WeatherServices()
        .getCityWeatherList("1701668,3067696,1835848");
    context
        .read<WeatherProvider>()
        .weatherDataReceived(weatherData['list']);
  }
}