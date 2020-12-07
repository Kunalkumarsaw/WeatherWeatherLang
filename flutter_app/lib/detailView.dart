import 'package:flutter/material.dart';
import "package:stacked/stacked.dart";
import 'viewModels/detailViewModel.dart';

class DetailView extends StatefulWidget {
  DetailView({this.weather});

  final dynamic weather;

  @override
  _DetailViewState createState() => _DetailViewState();
}

class _DetailViewState extends State<DetailView> {
  var weatherData;

  @override
  void initState() {
    super.initState();
    weatherData = widget.weather;
  }

  @override
  Widget build(BuildContext context) {
    return ViewModelBuilder<DetailViewModel>.reactive(
      viewModelBuilder: () => DetailViewModel(),
      onModelReady: (model) async {
        weatherData =
            await model.getSingleWeather(context, weatherData['id'].toString());
        setState(() {});
      },
      builder: (context, model, child) => Scaffold(
        appBar: AppBar(
          title: Text('DetailView'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(
                weatherData['name'],
              ),
              Text(
                (weatherData['main']['temp']).toString(),
              ),
              Text(
                weatherData['weather'][0]['main'],
              ),
              Text(
                "High: ${weatherData["main"]["temp_max"]} / Low: ${weatherData["main"]["temp_min"]} ",
              ),
            ],
          ),
        ),
      ),
    );
  }
}
