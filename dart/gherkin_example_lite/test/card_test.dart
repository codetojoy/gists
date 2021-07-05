import 'package:test/test.dart';

import 'package:gherkin_example_lite/card.dart';
import 'package:gherkin_example_lite/cards.dart';

void main() {
  group('Card', () {
    setUp(() {});
    test('isTrump hearts', () {
      const card = C.$QH;
      const suit = Suit.HEARTS;

      // test
      final result = card.isTrump(suit);

      expect(result, true);
    });
    test('isRed hearts', () {
      const card = C.$QH;

      // test
      final result = card.isRed;

      expect(result, true);
    });
    test('isBlack spades', () {
      const card = C.$QS;

      // test
      final result = card.isBlack;

      expect(result, true);
    });
  });
}
