import './card.dart';
import './constants.dart';

class C {
  static const UNKNOWN =
      Card(Ordinal.UNKNOWN, Suit.UNKNOWN, Const.UNKNOWN_CARD_ID);

  static const $AC = Card(Ordinal.ACE, Suit.CLUBS, 0);
  static const $2C = Card(Ordinal.TWO, Suit.CLUBS, 1);
  static const $3C = Card(Ordinal.THREE, Suit.CLUBS, 2);
  static const $4C = Card(Ordinal.FOUR, Suit.CLUBS, 3);
  static const $5C = Card(Ordinal.FIVE, Suit.CLUBS, 4);
  static const $6C = Card(Ordinal.SIX, Suit.CLUBS, 5);
  static const $7C = Card(Ordinal.SEVEN, Suit.CLUBS, 6);
  static const $8C = Card(Ordinal.EIGHT, Suit.CLUBS, 7);
  static const $9C = Card(Ordinal.NINE, Suit.CLUBS, 8);
  static const $10C = Card(Ordinal.TEN, Suit.CLUBS, 9);
  static const $JC = Card(Ordinal.JACK, Suit.CLUBS, 10);
  static const $QC = Card(Ordinal.QUEEN, Suit.CLUBS, 11);
  static const $KC = Card(Ordinal.KING, Suit.CLUBS, 12);

  static const $AD = Card(Ordinal.ACE, Suit.DIAMONDS, 13 + 0);
  static const $2D = Card(Ordinal.TWO, Suit.DIAMONDS, 13 + 1);
  static const $3D = Card(Ordinal.THREE, Suit.DIAMONDS, 13 + 2);
  static const $4D = Card(Ordinal.FOUR, Suit.DIAMONDS, 13 + 3);
  static const $5D = Card(Ordinal.FIVE, Suit.DIAMONDS, 13 + 4);
  static const $6D = Card(Ordinal.SIX, Suit.DIAMONDS, 13 + 5);
  static const $7D = Card(Ordinal.SEVEN, Suit.DIAMONDS, 13 + 6);
  static const $8D = Card(Ordinal.EIGHT, Suit.DIAMONDS, 13 + 7);
  static const $9D = Card(Ordinal.NINE, Suit.DIAMONDS, 13 + 8);
  static const $10D = Card(Ordinal.TEN, Suit.DIAMONDS, 13 + 9);
  static const $JD = Card(Ordinal.JACK, Suit.DIAMONDS, 13 + 10);
  static const $QD = Card(Ordinal.QUEEN, Suit.DIAMONDS, 13 + 11);
  static const $KD = Card(Ordinal.KING, Suit.DIAMONDS, 13 + 12);

  static const $AH = Card(Ordinal.ACE, Suit.HEARTS, 26 + 0);
  static const $2H = Card(Ordinal.TWO, Suit.HEARTS, 26 + 1);
  static const $3H = Card(Ordinal.THREE, Suit.HEARTS, 26 + 2);
  static const $4H = Card(Ordinal.FOUR, Suit.HEARTS, 26 + 3);
  static const $5H = Card(Ordinal.FIVE, Suit.HEARTS, 26 + 4);
  static const $6H = Card(Ordinal.SIX, Suit.HEARTS, 26 + 5);
  static const $7H = Card(Ordinal.SEVEN, Suit.HEARTS, 26 + 6);
  static const $8H = Card(Ordinal.EIGHT, Suit.HEARTS, 26 + 7);
  static const $9H = Card(Ordinal.NINE, Suit.HEARTS, 26 + 8);
  static const $10H = Card(Ordinal.TEN, Suit.HEARTS, 26 + 9);
  static const $JH = Card(Ordinal.JACK, Suit.HEARTS, 26 + 10);
  static const $QH = Card(Ordinal.QUEEN, Suit.HEARTS, 26 + 11);
  static const $KH = Card(Ordinal.KING, Suit.HEARTS, 26 + 12);

  static const $AS = Card(Ordinal.ACE, Suit.SPADES, 39 + 0);
  static const $2S = Card(Ordinal.TWO, Suit.SPADES, 39 + 1);
  static const $3S = Card(Ordinal.THREE, Suit.SPADES, 39 + 2);
  static const $4S = Card(Ordinal.FOUR, Suit.SPADES, 39 + 3);
  static const $5S = Card(Ordinal.FIVE, Suit.SPADES, 39 + 4);
  static const $6S = Card(Ordinal.SIX, Suit.SPADES, 39 + 5);
  static const $7S = Card(Ordinal.SEVEN, Suit.SPADES, 39 + 6);
  static const $8S = Card(Ordinal.EIGHT, Suit.SPADES, 39 + 7);
  static const $9S = Card(Ordinal.NINE, Suit.SPADES, 39 + 8);
  static const $10S = Card(Ordinal.TEN, Suit.SPADES, 39 + 9);
  static const $JS = Card(Ordinal.JACK, Suit.SPADES, 39 + 10);
  static const $QS = Card(Ordinal.QUEEN, Suit.SPADES, 39 + 11);
  static const $KS = Card(Ordinal.KING, Suit.SPADES, 39 + 12);

  static const List<Card> allCards = [
    // clubs
    $AC,
    $2C,
    $3C,
    $4C,
    $5C,
    $6C,
    $7C,
    $8C,
    $9C,
    $10C,
    $JC,
    $QC,
    $KC,
    // diamonds
    $AD,
    $2D,
    $3D,
    $4D,
    $5D,
    $6D,
    $7D,
    $8D,
    $9D,
    $10D,
    $JD,
    $QD,
    $KD,
    // hearts
    $AH,
    $2H,
    $3H,
    $4H,
    $5H,
    $6H,
    $7H,
    $8H,
    $9H,
    $10H,
    $JH,
    $QH,
    $KH,
    // spades
    $AS,
    $2S,
    $3S,
    $4S,
    $5S,
    $6S,
    $7S,
    $8S,
    $9S,
    $10S,
    $JS,
    $QS,
    $KS,
  ];

  static List<Card> getBySuit(Suit suit) {
    return allCards.where((card) => card.suit == suit).toList();
  }

  static List<Card> getRandomBySuit(Suit suit, int numCards) {
    final cards = List<Card>.from(getBySuit(suit));
    cards.shuffle();
    final result = cards.take(numCards).toList();
    return result;
  }
}
