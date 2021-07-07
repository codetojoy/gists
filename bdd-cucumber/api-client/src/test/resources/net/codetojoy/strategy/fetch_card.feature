Feature: Show card result from api

  Boilerplate text here

  Scenario: basic max
    Given prizeCard: 1 maxCard: 20 cards: "2,4,6"
    When mode "max"
    Then card selection should be 6

  Scenario: basic min
    Given prizeCard: 1 maxCard: 20 cards: "2,4,6"
    When mode "min"
    Then card selection should be 2

  Scenario: basic next
    Given prizeCard: 1 maxCard: 20 cards: "4,6,2"
    When mode "next"
    Then card selection should be 4

  Scenario: basic nearest
    Given prizeCard: 10 maxCard: 20 cards: "4,3,8,14,20"
    When mode "nearest"
    Then card selection should be 8
