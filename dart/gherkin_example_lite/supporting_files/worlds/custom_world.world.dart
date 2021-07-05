import 'package:gherkin/gherkin.dart';

import 'package:gherkin_example_lite/calculator.dart';

class CalculatorWorld extends World {
  final Calculator calculator = Calculator();

  @override
  void dispose() {
    calculator.dispose();
  }
}
