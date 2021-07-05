import 'dart:io' show Platform;

import 'package:glob/glob.dart';
import 'package:glob/list_local_fs.dart';

void main(List<String> arguments) {
  final pathSep = Platform.pathSeparator;
  print('TRACER pathSep: $pathSep');
  final path = 'data${pathSep}*.txt';
  final files = Glob(path);
  for (var entity in files.listSync()) {
    print('path: $entity.path');
  }
  print('Ready.');
}
