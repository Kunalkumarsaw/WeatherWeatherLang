
import 'package:flutter/foundation.dart';

class WeatherProvider with ChangeNotifier {
  List<dynamic> list = [];
  dynamic weather;
  bool dataReceived = false;

  void weatherDataReceived(List<dynamic> newList){
    list = newList;
    notifyListeners();
  }

  void singleWeatherDataReceived(dynamic newWeather){
    weather = newWeather;
    dataReceived = true;
    notifyListeners();
  }
}