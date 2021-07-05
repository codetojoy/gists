import 'dart:async';
import 'dart:io';
import 'package:gherkin/gherkin.dart';
import 'supporting_files/steps/given_the_numbers.step.dart';
import 'supporting_files/steps/then_expect_numeric_result.step.dart';
import 'supporting_files/steps/when_numbers_are_added.step.dart';
import 'supporting_files/worlds/custom_world.world.dart';

String buildFeaturesPathRegex() {
  // '\' must be escaped, '/' must not be escaped:
  final featuresPath = (Platform.isWindows)
      ? 'features\${Platform.pathSeparator}.*\.feature'
      : 'features${Platform.pathSeparator}.*\.feature';
  return featuresPath;
}

Future<void> main() {
  final steps = [
    GivenTheNumbers(),
    WhenTheStoredNumbersAreAdded(),
    ThenExpectNumericResult()
  ];
  final featuresPath = buildFeaturesPathRegex();
  final config = TestConfiguration.DEFAULT(steps)
    ..features = [RegExp(featuresPath)]
    ..createWorld = (TestConfiguration config) => Future.value(CalculatorWorld());

  return GherkinRunner().execute(config);
}
