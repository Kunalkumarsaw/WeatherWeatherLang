import 'package:http/http.dart' as http;
import 'dart:convert' as converter;

class NetworkHelper {
  final String url;
  NetworkHelper(this.url);

  Future getDataFromURL() async{
    http.Response response = await http.get(url);
    if (response.statusCode == 200)
    {
      return converter.jsonDecode(response.body);
    } else {
      return response.statusCode.toString();
    }
  }


}