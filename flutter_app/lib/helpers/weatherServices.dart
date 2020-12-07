
import '../helpers/networkHelper.dart';

class WeatherServices {
  final apiKey = 'a7d39c34549f2a5dcc4a03dbdd6c51ba';
  final openWeatherMapURl = 'https://api.openweathermap.org/data/2.5/group';
  final openWeatherMapByCityURl = 'https://api.openweathermap.org/data/2.5/weather';

  Future<dynamic> getCityWeatherList(String city) async {
    NetworkHelper networkHelper =
    NetworkHelper('$openWeatherMapURl?id=$city&appid=$apiKey&units=metric');

    var weatherData = await networkHelper.getDataFromURL();
    return weatherData;
  }

  Future<dynamic> getCityWeather(String city) async {
    NetworkHelper networkHelper =
    NetworkHelper('$openWeatherMapByCityURl?id=$city&appid=$apiKey&units=metric');

    var weatherData = await networkHelper.getDataFromURL();
    return weatherData;
  }

  String getWeatherIcon(int condition) {
    if (condition < 300) {
      return '🌩';
    } else if (condition < 400) {
      return '🌧';
    } else if (condition < 600) {
      return '☔️';
    } else if (condition < 700) {
      return '☃️';
    } else if (condition < 800) {
      return '🌫';
    } else if (condition == 800) {
      return '☀️';
    } else if (condition <= 804) {
      return '☁️';
    } else {
      return '🤷‍';
    }
  }

  String getMessage(int temp) {
    if (temp > 25) {
      return 'It\'s 🍦 time';
    } else if (temp > 20) {
      return 'Time for shorts and 👕';
    } else if (temp < 10) {
      return 'You\'ll need 🧣 and 🧤';
    } else {
      return 'Bring a 🧥 just in case';
    }
  }
}
