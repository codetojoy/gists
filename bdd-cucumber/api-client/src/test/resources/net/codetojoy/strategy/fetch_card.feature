Feature: Show card result from api

  Boilerplate text here

  Scenario: basic max
    Given prizeCard: 1 maxCard: 20 cards: "2,4,6" mode: "max"
    When I select card
    Then card selection should be 6

  Scenario: basic min
    Given prizeCard: 1 maxCard: 20 cards: "2,4,6" mode: "min"
    When I select card
    Then card selection should be 2

  Scenario: basic next
    Given prizeCard: 1 maxCard: 20 cards: "4,6,2" mode: "next"
    When I select card
    Then card selection should be 4

  Scenario: basic nearest
    Given prizeCard: 10 maxCard: 20 cards: "4,3,8,14,20" mode: "nearest"
    When I select card
    Then card selection should be 8
