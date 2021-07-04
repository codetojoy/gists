import './constants.dart';

class CardException implements Exception {
  String errMsg() => 'illegal card';
}

enum Ordinal {
  ACE, //  = 0,
  TWO, //  = 1,
  THREE, //  = 2,
  FOUR, //  = 3,
  FIVE, //  = 4,
  SIX, //  = 5,
  SEVEN, //  = 6,
  EIGHT, //  = 7,
  NINE, //  = 8,
  TEN, //  = 9,
  JACK, //  = 10,
  QUEEN, //  = 11,
  KING, //  = 12,
  UNKNOWN,
}

enum Suit {
  CLUBS, //  = 0,
  DIAMONDS, //  = 1,
  HEARTS, //  = 2,
  SPADES, //  = 3,
  UNKNOWN,
}

class Suits {
  Suit getRandom() {
    final suits = [Suit.CLUBS, Suit.DIAMONDS, Suit.HEARTS, Suit.SPADES];
    suits.shuffle();
    return suits[0];
  }
}

class Card {
  final int _id;
  final Suit _suit;
  final Ordinal _ordinal;

  bool get isUnknown => _suit == Suit.UNKNOWN || _ordinal == Ordinal.UNKNOWN;

  int get id => _id;
  Suit get suit => _suit;
  Ordinal get ordinal => _ordinal;

  const Card(this._ordinal, this._suit, this._id);

  bool get isRed {
    return _suit == Suit.DIAMONDS || _suit == Suit.HEARTS;
  }

  bool get isBlack {
    return _suit == Suit.CLUBS || _suit == Suit.SPADES;
  }

  bool get isAceOfHearts {
    return _ordinal == Ordinal.ACE && _suit == Suit.HEARTS;
  }

  bool isTrump(Suit trump) {
    return _suit == trump || isAceOfHearts;
  }

  bool isLeadingSuit(Suit leadingSuit) {
    return _suit == leadingSuit;
  }

/*
  String get imageURL {
    var ordStr = _buildOrdinalString();
    var suitStr = _buildSuitString();

    var result = 'assets/images/cards/${ordStr}${suitStr}.jpg';
    return result;
  }
*/

  String _buildOrdinalString() {
    var result = '';

    if (_ordinal == Ordinal.UNKNOWN) {
      throw CardException();
    }
    var resultOrd = _ordinal.index + 1;

    if (resultOrd < Const.SUIT_MIN_INDEX + 1 ||
        resultOrd > Const.SUIT_MAX_INDEX + 1) {
      throw CardException();
    }

    switch (resultOrd) {
      case 1:
        result = 'A';
        break;
      case 11:
        result = 'J';
        break;
      case 12:
        result = 'Q';
        break;
      case 13:
        result = 'K';
        break;
      default:
        result = '$resultOrd';
        break;
    }

    return result;
  }

  String _buildSuitString() {
    var result = '';

    if (_suit == Suit.UNKNOWN) {
      throw CardException();
    }

    switch (_suit) {
      case Suit.CLUBS:
        result = 'C';
        break;
      case Suit.DIAMONDS:
        result = 'D';
        break;
      case Suit.HEARTS:
        result = 'H';
        break;
      case Suit.SPADES:
        result = 'S';
        break;
      default:
        result = 'ERROR';
        break;
    }

    return result;
  }

  @override
  String toString() {
    var ordStr = _buildOrdinalString();
    var suitStr = _buildSuitString();
    return '$ordStr$suitStr';
  }
}
