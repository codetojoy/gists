import 'dart:convert';
import 'dart:io';

void handleGet(HttpRequest request) {
  final path = request.uri.path;

  print('TRACER path: $path');

  switch (path) {
    case '/v1':
      handleV1(request);
      break;
    case '/v2/foo':
      handleFoo(request);
      break;
    case '/v2/bar':
      handleBar(request);
      break;
    default:
      handleUnknown(request);
      break;
  }
}

void handleV1(HttpRequest request) {
  final uri = request.uri;
  final path = uri.path;
  final queryParams = uri.queryParameters;
  if (queryParams.containsKey('id')) {
    final id = queryParams['id'];
    print('TRACER id: $id');
  }
  final text = 'text: hello from get v1 cp b';
  request.response
    ..write(text)
    ..close();
}

void handleFoo(HttpRequest request) {
  final uri = request.uri;
  final path = uri.path;
  final queryParams = uri.queryParameters;
  if (queryParams.containsKey('id')) {
    final id = queryParams['id'];
    print('TRACER id: $id');
  }
  final text = 'text: hello from get foo';
  request.response
    ..write(text)
    ..close();
}

void handleBar(HttpRequest request) {
  final uri = request.uri;
  final queryParams = uri.queryParameters;
  var id = '0';
  if (queryParams.containsKey('id')) {
    id = queryParams['id']!;
  }
  final map = {
    'id': id,
    'name': 'mozart',
    'message': 'from bar',
  };
  final jsonString = jsonEncode(map);
  request.response.headers.set(HttpHeaders.contentTypeHeader, 'application/json');
  request.response
    ..write(jsonString)
    ..close();
}

void handleUnknown(HttpRequest request) {
  final text = 'error: not found';
  request.response.statusCode = HttpStatus.notFound;
  request.response
    ..write(text)
    ..close();
}
