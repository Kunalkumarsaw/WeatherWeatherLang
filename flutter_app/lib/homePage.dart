import 'package:flutter/material.dart';
import 'package:flutter_app/helpers/weatherProvider.dart';
import 'viewModels/homeViewModel.dart';
import "package:stacked/stacked.dart";
import 'package:provider/provider.dart';
import 'detailView.dart';

class HomePage extends StatefulWidget {
  HomePage({this.title});
  final String title;

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  Widget _listView(HomeViewModel model, BuildContext context) {
    return Expanded(
      child: ListView.builder(
        scrollDirection: Axis.vertical,
        shrinkWrap: true,
        itemCount: context.watch<WeatherProvider>().list.length,
        itemBuilder: (context, index) {
          var weather = context.watch<WeatherProvider>().list[index];
          return Card(
            child: ListTile(
              title: Text(
                weather['name'],
              ),
              subtitle: Text(
                (weather['main']['temp']).toString(),
              ),
              trailing: Text((weather['weather'][0]['description']).toString()),
              onTap: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) {
                  return DetailView(weather: weather);
                }));
              },
            ),
          );
        },
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return ViewModelBuilder<HomeViewModel>.reactive(
      viewModelBuilder: () => HomeViewModel(),
      onModelReady: (model) async {
        model.getWeatherList(context);
      },
      builder: (context, model, child) => Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              _listView(model, context),
            ],
          ),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () async {
            model.getWeatherList(context);
          },
          tooltip: 'Refresh',
          child: Icon(Icons.refresh_outlined),
        ),
      ),
    );
  }
}
