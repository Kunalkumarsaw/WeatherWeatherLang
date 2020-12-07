import 'package:flutter/material.dart';
import 'package:provider_architecture/_base_viewmodels.dart';
import '../helpers/weatherServices.dart';

class DetailViewModel extends BaseViewModel {
  Future<dynamic>  getSingleWeather(BuildContext context, String id) async{
    return await WeatherServices().getCityWeather(id);
  }



}