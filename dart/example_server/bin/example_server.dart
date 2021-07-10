import 'dart:convert';
import 'dart:io';

Future<void> main(List<String> args) async {
  final GAME_MODE = "game";
  final SERVER_MODE = "server";
  var mode = "N/A";

  if (args.isEmpty) {
    usage();
  } else {
    mode = args[0];
    if (mode != GAME_MODE && mode != SERVER_MODE) {
        usage();
    }
  }

  if (mode == "server") {
      final server = await createServer();
      print('Server started: ${server.address} port ${server.port}');
      await handleRequests(server);
  } else {
      print('TODO: game here');
  }
}

Future<void> handleRequests(HttpServer server) async {
  await for (HttpRequest request in server) {
    switch (request.method) {
      case 'GET':
        handleGet(request);
        break;
      case 'POST':
        handlePost(request);
        break;
      default:
        handleDefault(request);
    }
  }
}

Future<HttpServer> createServer() async {
  final address = InternetAddress.loopbackIPv4;
  const port = 4040;
  return await HttpServer.bind(address, port);
}

var myStringStorage = 'Hello from a Dart server';
void handleGet(HttpRequest request) {
  request.response
    ..write(myStringStorage)
    ..close();
}

Future<void> handlePost(HttpRequest request) async {
  myStringStorage = await utf8.decoder.bind(request).join();
  request.response
    ..write('Got it. Thanks.')
    ..close();
}

Future<void> handleDefault(HttpRequest request) async {
    // no-op
}

Future<void> usage() async {
  print('Usage: example_server.dart [game|server]');
  exit(-5150);
}
