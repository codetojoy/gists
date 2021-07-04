import 'package:test/test.dart';

import 'package:gherkin_sandbox/card.dart';
import 'package:gherkin_sandbox/cards.dart';

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
  /*
  group('Card ids', () {
    var valueMap = [
      {'id': 0, 'ord': Ordinal.ACE, 'suit': Suit.CLUBS},
      {'id': 1, 'ord': Ordinal.TWO, 'suit': Suit.CLUBS},
      {'id': 2, 'ord': Ordinal.THREE, 'suit': Suit.CLUBS},
      {'id': 3, 'ord': Ordinal.FOUR, 'suit': Suit.CLUBS},
      {'id': 4, 'ord': Ordinal.FIVE, 'suit': Suit.CLUBS},
      {'id': 5, 'ord': Ordinal.SIX, 'suit': Suit.CLUBS},
      {'id': 6, 'ord': Ordinal.SEVEN, 'suit': Suit.CLUBS},
      {'id': 7, 'ord': Ordinal.EIGHT, 'suit': Suit.CLUBS},
      {'id': 8, 'ord': Ordinal.NINE, 'suit': Suit.CLUBS},
      {'id': 9, 'ord': Ordinal.TEN, 'suit': Suit.CLUBS},
      {'id': 10, 'ord': Ordinal.JACK, 'suit': Suit.CLUBS},
      {'id': 11, 'ord': Ordinal.QUEEN, 'suit': Suit.CLUBS},
      {'id': 12, 'ord': Ordinal.KING, 'suit': Suit.CLUBS},
      {'id': 13, 'ord': Ordinal.ACE, 'suit': Suit.DIAMONDS},
      {'id': 14, 'ord': Ordinal.TWO, 'suit': Suit.DIAMONDS},
      {'id': 15, 'ord': Ordinal.THREE, 'suit': Suit.DIAMONDS},
      {'id': 16, 'ord': Ordinal.FOUR, 'suit': Suit.DIAMONDS},
      {'id': 17, 'ord': Ordinal.FIVE, 'suit': Suit.DIAMONDS},
      {'id': 18, 'ord': Ordinal.SIX, 'suit': Suit.DIAMONDS},
      {'id': 19, 'ord': Ordinal.SEVEN, 'suit': Suit.DIAMONDS},
      {'id': 20, 'ord': Ordinal.EIGHT, 'suit': Suit.DIAMONDS},
      {'id': 21, 'ord': Ordinal.NINE, 'suit': Suit.DIAMONDS},
      {'id': 22, 'ord': Ordinal.TEN, 'suit': Suit.DIAMONDS},
      {'id': 23, 'ord': Ordinal.JACK, 'suit': Suit.DIAMONDS},
      {'id': 24, 'ord': Ordinal.QUEEN, 'suit': Suit.DIAMONDS},
      {'id': 25, 'ord': Ordinal.KING, 'suit': Suit.DIAMONDS},
      {'id': 26, 'ord': Ordinal.ACE, 'suit': Suit.HEARTS},
      {'id': 27, 'ord': Ordinal.TWO, 'suit': Suit.HEARTS},
      {'id': 28, 'ord': Ordinal.THREE, 'suit': Suit.HEARTS},
      {'id': 29, 'ord': Ordinal.FOUR, 'suit': Suit.HEARTS},
      {'id': 30, 'ord': Ordinal.FIVE, 'suit': Suit.HEARTS},
      {'id': 31, 'ord': Ordinal.SIX, 'suit': Suit.HEARTS},
      {'id': 32, 'ord': Ordinal.SEVEN, 'suit': Suit.HEARTS},
      {'id': 33, 'ord': Ordinal.EIGHT, 'suit': Suit.HEARTS},
      {'id': 34, 'ord': Ordinal.NINE, 'suit': Suit.HEARTS},
      {'id': 35, 'ord': Ordinal.TEN, 'suit': Suit.HEARTS},
      {'id': 36, 'ord': Ordinal.JACK, 'suit': Suit.HEARTS},
      {'id': 37, 'ord': Ordinal.QUEEN, 'suit': Suit.HEARTS},
      {'id': 38, 'ord': Ordinal.KING, 'suit': Suit.HEARTS},
      {'id': 39, 'ord': Ordinal.ACE, 'suit': Suit.SPADES},
      {'id': 40, 'ord': Ordinal.TWO, 'suit': Suit.SPADES},
      {'id': 41, 'ord': Ordinal.THREE, 'suit': Suit.SPADES},
      {'id': 42, 'ord': Ordinal.FOUR, 'suit': Suit.SPADES},
      {'id': 43, 'ord': Ordinal.FIVE, 'suit': Suit.SPADES},
      {'id': 44, 'ord': Ordinal.SIX, 'suit': Suit.SPADES},
      {'id': 45, 'ord': Ordinal.SEVEN, 'suit': Suit.SPADES},
      {'id': 46, 'ord': Ordinal.EIGHT, 'suit': Suit.SPADES},
      {'id': 47, 'ord': Ordinal.NINE, 'suit': Suit.SPADES},
      {'id': 48, 'ord': Ordinal.TEN, 'suit': Suit.SPADES},
      {'id': 49, 'ord': Ordinal.JACK, 'suit': Suit.SPADES},
      {'id': 50, 'ord': Ordinal.QUEEN, 'suit': Suit.SPADES},
      {'id': 51, 'ord': Ordinal.KING, 'suit': Suit.SPADES},
    ];
    valueMap.forEach((values) {
      test('$values.n', () {
        final ordinal = values['ord'] as Ordinal;
        final suit = values['suit'] as Suit;

        // test
        final result = Card(ordinal, suit);

        final expectedId = values['id'] as int;
        expect(result.id, expectedId);
      });
    }); // valueMap
  }); // group
  */
}
